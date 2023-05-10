package com.tuxsnct.inkwell.model

import java.io.File
import java.util.UUID

enum class FileType {
    NOTE,
    TEMPLATE,
    FOLDER
}

abstract class AppSpecificFile {
    abstract val type: FileType
    lateinit var file: File
    lateinit var uuid: UUID
    lateinit var name: String

    abstract fun save()
    fun move(parent: File?) = file.renameTo(File(parent ?: file.parentFile, file.name))
    fun copy(): Boolean = file.copyRecursively(File(file.parent, "${UUID.randomUUID()}"))
    fun delete(): Boolean = file.deleteRecursively()
    abstract fun rename(newName: String)

    companion object {
        fun load(file: File): AppSpecificFile {
            val name = file.nameWithoutExtension
            val type = when (file.extension) {
                "iwnote" -> FileType.NOTE
                "iwtmpl" -> FileType.TEMPLATE
                else -> {
                    if (file.isDirectory) FileType.FOLDER
                    else throw IllegalArgumentException("Invalid file type")
                }
            }

            return when (type) {
                FileType.NOTE -> Note(file, name)
                FileType.TEMPLATE -> Template(file, name)
                FileType.FOLDER -> Folder(file, name, "#000000")
            }
        }
    }
}