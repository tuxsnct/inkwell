package com.tuxsnct.inkwell.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.tuxsnct.inkwell.model.Folder
import com.tuxsnct.inkwell.model.Template
import com.tuxsnct.inkwell.ui.components.manager.ManagerFilesGrid

@Composable
fun TemplatesTab(
    navigateToEditor: (Folder) -> Unit
) {
    val templates = remember { mutableStateListOf<Folder>() }

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        Template.observeFolders(context, lifecycleOwner) {
            templates.clear()
            templates.addAll(it)
        }
    }

    ManagerFilesGrid(templates, navigateToEditor)
}