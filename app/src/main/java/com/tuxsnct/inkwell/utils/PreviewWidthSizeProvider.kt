package com.tuxsnct.inkwell.utils

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class PreviewWidthSizeProvider : PreviewParameterProvider<WindowWidthSizeClass> {
    override val values: Sequence<WindowWidthSizeClass>
        get() = sequenceOf(
            WindowWidthSizeClass.Compact,
            WindowWidthSizeClass.Medium,
            WindowWidthSizeClass.Expanded
        )
}