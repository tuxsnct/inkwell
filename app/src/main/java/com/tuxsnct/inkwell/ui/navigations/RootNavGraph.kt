package com.tuxsnct.inkwell.ui.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tuxsnct.inkwell.ui.views.*

@Composable
fun RootNavGraph(
    isCompact: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = RootDestinations.MANAGER.name
) {
    val rootNavActions = RootNavActions(navController)

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(RootDestinations.MANAGER.name) {
            ManagerScreen(
                isCompact,
                rootNavActions.navigateToSearch,
                rootNavActions.navigateToSettings,
                rootNavActions.navigateToEditor
            )
        }
        composable(RootDestinations.EDITOR.name) {
            EditorScreen(
                isCompact,
                rootNavActions.navigateToManager
            )
        }
        composable(RootDestinations.SEARCH.name) {
            SearchScreen(
                isCompact,
                rootNavActions.popBackStack
            )
        }
        composable(RootDestinations.SETTINGS.name) {
            SettingsScreen(
                isCompact,
                rootNavActions.popBackStack
            )
        }
    }
}
