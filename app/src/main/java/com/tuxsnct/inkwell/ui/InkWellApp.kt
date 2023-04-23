package com.tuxsnct.inkwell.ui

import androidx.compose.runtime.Composable
import com.tuxsnct.inkwell.ui.navigations.RootNavGraph
import com.tuxsnct.inkwell.ui.theme.InkWellTheme

@Composable
fun InkWellApp(
    isCompact: Boolean
) {
    InkWellTheme {
        RootNavGraph(isCompact)
    }
}
