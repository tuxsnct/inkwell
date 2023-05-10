package com.tuxsnct.inkwell.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.tuxsnct.inkwell.model.AppSpecificFile
import com.tuxsnct.inkwell.model.Template
import com.tuxsnct.inkwell.ui.components.manager.ManagerFilesGrid

@Composable
fun TemplatesTab(
    navigateToEditor: (AppSpecificFile) -> Unit
) {
    val context = LocalContext.current
    val templates = Template.getTemplatesDir(context).listFiles()?.map { AppSpecificFile.load(it) }
        ?: emptyList()
    ManagerFilesGrid(templates, navigateToEditor)
}