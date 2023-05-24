package com.tuxsnct.inkwell.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.tuxsnct.inkwell.model.file.Folder
import com.tuxsnct.inkwell.model.file.Note
import com.tuxsnct.inkwell.ui.components.manager.ManagerFilesGrid

@Composable
fun NotesTab(
    navigateToEditor: (Folder) -> Unit
) {
    val notes = remember { mutableStateListOf<Folder>() }

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        Note.observeFolders(context, lifecycleOwner) {
            notes.clear()
            notes.addAll(it)
        }
    }

    ManagerFilesGrid(notes, navigateToEditor)
}