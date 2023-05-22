package com.tuxsnct.inkwell.model

import androidx.datastore.core.DataStore
import com.tuxsnct.inkwell.FolderMetadataOuterClass.FolderMetadata
import com.tuxsnct.inkwell.FolderMetadataOuterClass.FolderType
import java.io.File

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
}