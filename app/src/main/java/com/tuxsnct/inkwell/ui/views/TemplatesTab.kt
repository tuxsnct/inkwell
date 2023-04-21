package com.tuxsnct.inkwell.ui.views

import androidx.compose.runtime.Composable
import com.tuxsnct.inkwell.model.File
import com.tuxsnct.inkwell.model.Template
import com.tuxsnct.inkwell.ui.components.manager.ManagerFilesGrid

@Composable
fun TemplatesTab(
    navigateToEditor: (File) -> Unit
) {
    val notes = (1..10).map {
        Template("$it")
    }
    ManagerFilesGrid(notes, navigateToEditor)
}