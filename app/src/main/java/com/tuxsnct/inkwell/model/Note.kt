package com.tuxsnct.inkwell.model

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import com.tuxsnct.inkwell.FolderMetadataOuterClass.FolderMetadata
import com.tuxsnct.inkwell.FolderMetadataOuterClass.FolderType
import java.io.File

class Note(
    override val file: File
) : Folder() {
    override val type: FolderType = FolderType.NOTE
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
            val notesDir = File(context.filesDir, "notes")
            if (!notesDir.exists()) notesDir.mkdir()
            return notesDir
        }

        fun list(context: Context): List<Note> {
            val notesDir = getDir(context)
            return notesDir.listFiles()?.map { Note(it) } ?: emptyList()
        }
    }
}