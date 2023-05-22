package com.tuxsnct.inkwell.model

import android.content.Context
import androidx.datastore.core.DataStore
import com.tuxsnct.inkwell.FolderMetadataOuterClass.FolderMetadata
import com.tuxsnct.inkwell.FolderMetadataOuterClass.FolderType
import java.io.File

class Template(
    override val file: File,
    override val metadataStore: DataStore<FolderMetadata>
) : Folder() {
    override val type: FolderType = FolderType.TEMPLATE

    override fun save() {
        TODO()
    }

    override fun rename(newName: String) {
        TODO()
    }

    companion object {
        var folders = emptyList<Folder>()

        fun getDir(context: Context): File {
            val templatesDir = File(context.filesDir, "templates")
            if (!templatesDir.exists()) templatesDir.mkdir()
            return templatesDir
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