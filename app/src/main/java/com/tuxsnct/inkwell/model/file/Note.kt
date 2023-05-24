package com.tuxsnct.inkwell.model.file

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.tuxsnct.inkwell.FolderMetadataOuterClass.FolderMetadata
import com.tuxsnct.inkwell.FolderMetadataOuterClass.FolderType
import java.io.File
import java.util.UUID

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
        private val folders = MutableLiveData<List<Folder>>(emptyList())

        fun addFolder(folder: Folder) {
            folders.value = (folders.value ?: emptyList()) + folder
        }

        private fun removeFolder(folder: Folder) {
            folders.value = (folders.value ?: emptyList()) - folder
        }

        suspend fun observeFolders(context: Context, owner: LifecycleOwner, observer: Observer<List<Folder>>) {
            updateFolders(context)
            folders.observe(owner, observer)
        }

        fun getDir(context: Context): File {
            val notesDir = File(context.filesDir, "notes")
            if (!notesDir.exists() && !notesDir.mkdir()) throw FileSystemException(notesDir)
            return notesDir
        }

        suspend fun create(parent: File): Note {
            val file = File(parent, UUID.randomUUID().toString())
            val metadataStore = getMetadataStore(file)
            metadataStore.updateData { metadata ->
                metadata.toBuilder().setType(FolderType.NOTE).build()
            }

            val folder = Note(file, metadataStore)
            addFolder(folder)

            return folder
        }

        suspend fun updateFolders(context: Context) {
            if (folders.value?.isEmpty() == true) {
                folders.value = getDir(context).listFiles()?.map {
                    load(it, getMetadataStore(it))
                } ?: emptyList()
            }
        }
    }
}