package com.tuxsnct.inkwell.ui.navigations

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.tuxsnct.inkwell.ui.viewmodels.EditorViewModel
import com.tuxsnct.inkwell.ui.views.NotesTab
import com.tuxsnct.inkwell.ui.views.TemplatesTab

enum class ManagerDestinations {
    NOTES,
    TEMPLATES
}

fun NavGraphBuilder.managerNavGraph(
    isCompact: Boolean,
    navigateToEditor: () -> Unit,
    editorViewModel: EditorViewModel
) {
    navigation(ManagerDestinations.NOTES.name, RootDestinations.MANAGER.name) {
        composable(ManagerDestinations.NOTES.name) {
            NotesTab {
                editorViewModel.updateFile(it)
                navigateToEditor()
            }
        }
        composable(ManagerDestinations.TEMPLATES.name) {
            TemplatesTab {
                editorViewModel.updateFile(it)
                navigateToEditor()
            }
        }
    }
}

@Composable
fun ManagerNavGraph(
    isCompact: Boolean,
    navigateToEditor: () -> Unit,
    navController: NavHostController
) {
    val editorViewModel: EditorViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = RootDestinations.MANAGER.name
    ) {
        managerNavGraph(isCompact, navigateToEditor, editorViewModel)
    }
}