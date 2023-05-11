package com.tuxsnct.inkwell.model

import java.io.File
import java.util.UUID

class Folder(
    file: File,
    name: String,
    color: String
) : AppSpecificFile() {
    override val type: AppSpecificFileType = AppSpecificFileType.FOLDER
    var color: String

    init {
        this.file = file
        this.uuid = UUID.fromString(file.nameWithoutExtension)
        this.name = name
        this.color = color
    }

    override fun save() {
        TODO()
    }

    override fun rename(newName: String) {
        name = newName
    }

    companion object {
        fun create(parent: File, name: String?, color: String?): Folder {
            val file = File(parent, UUID.randomUUID().toString())

            if (!file.exists()) {
                file.mkdir()
                return Folder(file, name ?: "Untitled", color ?: "#000000")
            }

            throw FileAlreadyExistsException(file)
        }
    }
}