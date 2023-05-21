package com.tuxsnct.inkwell.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.tuxsnct.inkwell.model.Folder
import com.tuxsnct.inkwell.model.Note
import com.tuxsnct.inkwell.ui.components.manager.ManagerFilesGrid
import com.tuxsnct.inkwell.ui.viewmodels.ManagerViewModel

@Composable
fun NotesTab(
    managerViewModel: ManagerViewModel,
    navigateToEditor: (Folder) -> Unit
) {
    val notes = Note.getDir(LocalContext.current).listFiles()?.map { Note(it) } ?: emptyList()
    ManagerFilesGrid(notes, navigateToEditor, managerViewModel)
}