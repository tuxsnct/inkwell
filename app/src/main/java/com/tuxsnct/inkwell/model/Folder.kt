package com.tuxsnct.inkwell.model

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.tuxsnct.inkwell.FolderMetadataOuterClass.FolderMetadata
import com.tuxsnct.inkwell.FolderMetadataOuterClass.FolderType
import com.tuxsnct.inkwell.FolderMetadataOuterClass.FolderType.*
import kotlinx.coroutines.flow.first
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream
import java.io.OutputStream
import java.util.UUID

object FolderMetadataSerializer : Serializer<FolderMetadata> {
    override val defaultValue: FolderMetadata = FolderMetadata.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): FolderMetadata {
        try {
            return FolderMetadata.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: FolderMetadata, output: OutputStream) = t.writeTo(output)
}

object FolderMetadataStoreHolder {
    private var instance: DataStore<FolderMetadata>? = null

    fun getInstance(file: File): DataStore<FolderMetadata> {
        if (instance == null) {
            instance = DataStoreFactory.create(
                serializer = FolderMetadataSerializer
            ) {
                if (!file.exists()) file.mkdir()
                File(file.path, "metadata.pb")
            }
        }
        return instance!!
    }
}

abstract class Folder {
    abstract val type: FolderType
    abstract val file: File
    abstract val metadataStore: DataStore<FolderMetadata>

    abstract fun save()
    fun move(parent: File?) = file.renameTo(File(parent ?: file.parentFile, file.name))
    fun copy(): Boolean = file.copyRecursively(File(file.parent, "${UUID.randomUUID()}"))
    fun delete(): Boolean = file.deleteRecursively()
    abstract fun rename(newName: String)

    companion object {
        fun getMetadataStore(file: File): DataStore<FolderMetadata> {
            return DataStoreFactory.create(
                serializer = FolderMetadataSerializer
            ) {
                if (!file.exists()) file.mkdir()
                File(file.path, "metadata.pb")
            }
        }

        suspend fun create(context: Context, folderType: FolderType, parent: File): Folder {
            val file = File(parent, UUID.randomUUID().toString())
            val metadataStore = getMetadataStore(file)
            metadataStore.updateData { metadata ->
                metadata.toBuilder().setType(folderType).build()
            }

            val folder = when (folderType) {
                COLLECTION -> Collection(file, metadataStore)
                NOTE -> Note(file, metadataStore)
                TEMPLATE -> Template(file, metadataStore)
                else -> throw FileNotFoundException()
            }

            when (folder.file.path.startsWith(Note.getDir(context).path)) {
                true -> Note.folders += folder
                false -> Template.folders += folder
            }

            return folder
        }

        suspend fun load(file: File, metadataStore: DataStore<FolderMetadata>): Folder {
            return when (metadataStore.data.first().type) {
                COLLECTION -> Collection(file, metadataStore)
                NOTE -> Note(file, metadataStore)
                TEMPLATE -> Template(file, metadataStore)
                else -> throw FileNotFoundException()
            }
        }
    }
}
