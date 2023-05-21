package com.tuxsnct.inkwell.ui.views

import androidx.compose.runtime.Composable
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
    val templates = Template.list(LocalContext.current)
    ManagerFilesGrid(templates, navigateToEditor, managerViewModel)
}