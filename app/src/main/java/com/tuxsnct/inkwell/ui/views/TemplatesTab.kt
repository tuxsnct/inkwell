package com.tuxsnct.inkwell.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.tuxsnct.inkwell.model.Folder
import com.tuxsnct.inkwell.model.Template
import com.tuxsnct.inkwell.ui.components.manager.ManagerFilesGrid
import com.tuxsnct.inkwell.ui.viewmodels.ManagerViewModel

@Composable
fun TemplatesTab(
    managerViewModel: ManagerViewModel,
    navigateToEditor: (Folder) -> Unit
) {
    var templates by rememberSaveable { mutableStateOf(emptyList<Folder>()) }

    val context = LocalContext.current
    LaunchedEffect(context) {
        templates = Template.list(context)
    }

    ManagerFilesGrid(templates, navigateToEditor, managerViewModel)
}