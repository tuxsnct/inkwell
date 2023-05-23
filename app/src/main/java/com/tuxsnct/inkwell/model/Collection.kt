package com.tuxsnct.inkwell.model

import android.content.Context
import androidx.datastore.core.DataStore
import com.tuxsnct.inkwell.FolderMetadataOuterClass.FolderMetadata
import com.tuxsnct.inkwell.FolderMetadataOuterClass.FolderType
import java.io.File
import java.util.UUID

class Collection(
    override val file: File,
    override val metadataStore: DataStore<FolderMetadata>
) : Folder() {
    override val type: FolderType = FolderType.COLLECTION

    override fun save() {
        TODO()
    }

    override fun rename(newName: String) {
        TODO()
    }

    companion object {
        suspend fun create(context: Context, parent: File): Collection {
            val file = File(parent, UUID.randomUUID().toString())
            val metadataStore = getMetadataStore(file)
            metadataStore.updateData { metadata ->
                metadata.toBuilder().setType(FolderType.COLLECTION).build()
            }

            val folder = Collection(file, metadataStore)
            when (file.path.startsWith(Note.getDir(context).path)) {
                true -> Note.addFolder(folder)
                false -> Template.addFolder(folder)
            }

            return folder
        }
    }
}