package com.tuxsnct.inkwell.ui.navigations

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tuxsnct.inkwell.ui.views.*

@Composable
fun RootNavGraph(
    widthSizeClass: WindowWidthSizeClass,
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
                widthSizeClass,
                rootNavActions.navigateToSearch,
                rootNavActions.navigateToSettings,
                rootNavActions.navigateToEditor
            )
        }
        composable(RootDestinations.EDITOR.name) {
            EditorScreen(
                widthSizeClass
            )
        }
        composable(RootDestinations.SEARCH.name) {
            SearchScreen(
                widthSizeClass,
                rootNavActions.popBackStack
            )
        }
        composable(RootDestinations.SETTINGS.name) {
            SettingsScreen(
                widthSizeClass,
                rootNavActions.popBackStack
            )
        }
    }
}
