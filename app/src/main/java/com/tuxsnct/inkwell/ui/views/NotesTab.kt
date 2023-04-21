package com.tuxsnct.inkwell.ui.views

import androidx.compose.runtime.Composable
import com.tuxsnct.inkwell.model.File
import com.tuxsnct.inkwell.model.Note
import com.tuxsnct.inkwell.ui.components.manager.ManagerFilesGrid

@Composable
fun NotesTab(
    navigateToEditor: (File) -> Unit
) {
    val notes = (1..10).map {
        Note("$it")
    }
    ManagerFilesGrid(notes, navigateToEditor)
}