package com.tuxsnct.inkwell.model

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import com.tuxsnct.inkwell.FolderMetadataOuterClass.FolderMetadata
import com.tuxsnct.inkwell.FolderMetadataOuterClass.FolderType
import java.io.File

class Template(
    override val file: File
) : Folder() {
    override val type: FolderType = FolderType.TEMPLATE
    override val metadataStore: DataStore<FolderMetadata> = DataStoreFactory.create(
        serializer = FolderMetadataSerializer
    ) {
        if (!this.file.exists()) this.file.mkdir()
        File(this.file.path, "metadata.pb")
    }

    override fun save() {
        TODO()
    }

    override fun rename(newName: String) {
        TODO()
    }

    companion object {
        fun getDir(context: Context): File {
            val templatesDir = File(context.filesDir, "templates")
            if (!templatesDir.exists()) templatesDir.mkdir()
            return templatesDir
        }

        fun list(context: Context): List<Template> {
            val templatesDir = getDir(context)
            return templatesDir.listFiles()?.map { Template(it) } ?: emptyList()
        }
    }
}