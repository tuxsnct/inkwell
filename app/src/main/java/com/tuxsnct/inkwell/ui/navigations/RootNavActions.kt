package com.tuxsnct.inkwell.ui.navigations

import androidx.navigation.NavHostController

enum class RootDestinations {
    MANAGER,
    EDITOR,
    SEARCH,
    SETTINGS
}

class RootNavActions(
    navController: NavHostController,
) {
    val navigateToManager: () -> Unit = {
        navController.navigate(RootDestinations.MANAGER.name)
    }
    val navigateToEditor: () -> Unit = {
        navController.navigate(RootDestinations.EDITOR.name)
    }
    val navigateToSearch: () -> Unit = {
        navController.navigate(RootDestinations.SEARCH.name)
    }
    val navigateToSettings: () -> Unit = {
        navController.navigate(RootDestinations.SETTINGS.name)
    }
    val popBackStack: () -> Unit = {
        navController.popBackStack()
    }
}