package com.tuxsnct.inkwell.model

import java.io.File
import java.util.UUID

abstract class AppSpecificFile {
    enum class AppSpecificFileType {
        NOTE,
        TEMPLATE,
        FOLDER
    }

    abstract val type: AppSpecificFileType
    lateinit var file: File
    lateinit var uuid: UUID
    lateinit var name: String

    abstract fun save()
    fun move(parent: File?) = file.renameTo(File(parent ?: file.parentFile, file.name))
    fun copy(): Boolean = file.copyRecursively(File(file.parent, "${UUID.randomUUID()}"))
    fun delete(): Boolean = file.deleteRecursively()
    abstract fun rename(newName: String)

    companion object {
        private fun detectFileType(file: File): AppSpecificFileType {
            return when (file.extension) {
                "iwnote" -> AppSpecificFileType.NOTE
                "iwtmpl" -> AppSpecificFileType.TEMPLATE
                else -> {
                    if (file.isDirectory) AppSpecificFileType.FOLDER
                    else throw IllegalArgumentException("Invalid file type")
                }
            }
        }

        fun load(file: File): AppSpecificFile {
            val name = file.nameWithoutExtension

            return when (detectFileType(file)) {
                AppSpecificFileType.NOTE -> Note(file, name)
                AppSpecificFileType.TEMPLATE -> Template(file, name)
                AppSpecificFileType.FOLDER -> Folder(file, name, "#000000")
            }
        }
    }
}