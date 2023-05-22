package com.tuxsnct.inkwell.model

import android.content.Context
import androidx.datastore.core.DataStore
import com.tuxsnct.inkwell.FolderMetadataOuterClass.FolderMetadata
import com.tuxsnct.inkwell.FolderMetadataOuterClass.FolderType
import java.io.File

class Note(
    override val file: File,
    override val metadataStore: DataStore<FolderMetadata>
) : Folder() {
    override val type: FolderType = FolderType.NOTE

    override fun save() {
        TODO()
    }

    override fun rename(newName: String) {
        TODO()
    }

    companion object {
        private var folders: List<Folder> = emptyList()

        fun getDir(context: Context): File {
            val notesDir = File(context.filesDir, "notes")
            if (!notesDir.exists()) notesDir.mkdir()
            return notesDir
        }

        suspend fun list(context: Context): List<Folder> {
            if (folders.isEmpty()) {
                folders = getDir(context).listFiles()?.map {
                    println(it.path)
                    load(it, getMetadataStore(it))
                } ?: emptyList()
            }
            return folders
        }
    }
}