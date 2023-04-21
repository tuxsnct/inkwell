package com.tuxsnct.inkwell.model

enum class FileType {
    NOTES,
    TEMPLATES
}

open class File constructor(
    var path: String,
    val type: FileType
) {
    val updatedAt: Long? = null
    val createdAt: Long? = null

    fun load() {}

    fun save() {}

    fun delete() {}

    fun copy() {}

    fun move() {}
}