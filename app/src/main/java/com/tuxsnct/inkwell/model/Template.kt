package com.tuxsnct.inkwell.model

import android.content.Context
import java.io.File
import java.util.UUID

class Template(
    file: File,
    name: String?
) : AppSpecificFile() {
    override val type: FileType = FileType.TEMPLATE

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
        fun getTemplatesDir(context: Context): File {
            val templatesDir = File(context.filesDir, "templates")
            if (!templatesDir.exists()) templatesDir.mkdir()
            return templatesDir
        }

        private fun create(parent: File, name: String?): Template {
            val file = File(parent, "${UUID.randomUUID()}.iwtmpl")

            if (!file.exists()) {
                file.createNewFile()
                return Template(file, name)
            }

            throw FileAlreadyExistsException(file)
        }

        fun create(context: Context, name: String?): Template {
            val templatesDir = getTemplatesDir(context)
            return create(templatesDir, name)
        }

        fun create(folder: Folder, name: String?): Template = create(folder.file, name)
    }
}