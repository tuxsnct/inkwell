package com.tuxsnct.inkwell.model

class Note(
    path: String,
) : File(
    path = path,
    type = FileType.NOTES
) {}