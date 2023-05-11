package com.tuxsnct.inkwell.model

import android.content.Context
import java.io.File
import java.util.UUID

class Note(
    file: File,
    name: String?
) : AppSpecificFile() {
    override val type: AppSpecificFileType = AppSpecificFileType.NOTE

    init {
        this.file = file
        this.uuid = UUID.fromString(file.nameWithoutExtension)
        this.name = name ?: "Untitled"
    }

    override fun save() {
        TODO()
    }

    override fun rename(newName: String) {
        name = newName
    }

    companion object {
        fun getNotesDir(context: Context): File {
            val notesDir = File(context.filesDir, "notes")
            if (!notesDir.exists()) notesDir.mkdir()
            return notesDir
        }

        private fun create(parent: File, name: String?): Note {
            val file = File(parent, "${UUID.randomUUID()}.iwnote")

            if (!file.exists()) {
                file.createNewFile()
                return Note(file, name)
            }

            throw FileAlreadyExistsException(file)
        }

        fun create(context: Context, name: String?): Note {
            val notesDir = getNotesDir(context)
            return create(notesDir, name)
        }

        fun create(folder: Folder, name: String?): Note = create(folder.file, name)
    }
}