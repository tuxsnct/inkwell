package com.tuxsnct.inkwell.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.tuxsnct.inkwell.model.AppSpecificFile
import com.tuxsnct.inkwell.model.Folder
import com.tuxsnct.inkwell.model.Note
import com.tuxsnct.inkwell.ui.components.manager.ManagerFilesGrid
import java.io.File

@Composable
fun NotesTab(
    navigateToEditor: (AppSpecificFile) -> Unit
) {
    val context = LocalContext.current
    val notes = Note.getNotesDir(context).listFiles()?.map { AppSpecificFile.load(it) }
        ?: emptyList()
    ManagerFilesGrid(notes, navigateToEditor)
}