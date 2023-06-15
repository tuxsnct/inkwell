package com.tuxsnct.inkwell.utils

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class PreviewBooleanProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(true, false)
}