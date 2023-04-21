package com.tuxsnct.inkwell.model

class Template(
    path: String
) : File(
    path = path,
    type = FileType.TEMPLATES
) {}