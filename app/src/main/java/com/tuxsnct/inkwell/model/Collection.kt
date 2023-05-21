package com.tuxsnct.inkwell.model

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import com.tuxsnct.inkwell.FolderMetadataOuterClass.FolderMetadata
import com.tuxsnct.inkwell.FolderMetadataOuterClass.FolderType
import java.io.File

class Collection(
    override val file: File
) : Folder() {
    override val type: FolderType = FolderType.COLLECTION
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
}