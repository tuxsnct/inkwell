package com.tuxsnct.inkwell.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tuxsnct.inkwell.ui.components.common.ResponsiveNavigation
import com.tuxsnct.inkwell.ui.components.common.ResponsiveNavigationType
import com.tuxsnct.inkwell.ui.components.manager.ManagerAppBar
import com.tuxsnct.inkwell.ui.components.manager.ManagerBottomSheet
import com.tuxsnct.inkwell.ui.components.manager.ManagerFloatingActionButton
import com.tuxsnct.inkwell.ui.components.manager.ManagerTabItem
import com.tuxsnct.inkwell.ui.navigations.ManagerDestinations
import com.tuxsnct.inkwell.ui.navigations.ManagerNavGraph
import com.tuxsnct.inkwell.ui.viewmodels.ManagerViewModel
import com.tuxsnct.inkwell.utils.AllPreviews
import com.tuxsnct.inkwell.utils.PreviewWidthSizeProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManagerScreen(
    widthSizeClass: WindowWidthSizeClass,
    navigateToSearch: () -> Unit,
    navigateToSettings: () -> Unit,
    navigateToEditor: () -> Unit,
    managerViewModel: ManagerViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination: NavDestination? = backStackEntry?.destination
    val tabs = listOf(
        ManagerTabItem(
            icon = { Icon(Icons.Default.Book, contentDescription = "Notes") },
            label = "Notes",
            route = ManagerDestinations.NOTES.name
        ),
        ManagerTabItem(
            icon = { Icon(Icons.Default.Description, contentDescription = "Templates") },
            label = "Templates",
            route = ManagerDestinations.TEMPLATES.name
        )
    )
    fun onTabClick(route: String) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    Scaffold(
        topBar = { ManagerAppBar(widthSizeClass, navigateToSearch, navigateToSettings) },
        bottomBar = {
            if (widthSizeClass == WindowWidthSizeClass.Compact) {
                ResponsiveNavigation(
                    ResponsiveNavigationType.NavigationBar,
                    tabs,
                    currentDestination
                ) { onTabClick(it) }
            }
        },
        floatingActionButton = {
            ManagerFloatingActionButton { managerViewModel.openBottomSheet() }
        }
    ) { contentPadding ->
        Row(modifier = Modifier.padding(contentPadding)) {
            if (widthSizeClass != WindowWidthSizeClass.Compact) {
                ResponsiveNavigation(
                    ResponsiveNavigationType.NavigationRail,
                    tabs,
                    currentDestination
                )  { onTabClick(it) }
            }
            ManagerNavGraph(navController, navigateToEditor)
        }
    }

    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = managerViewModel.skipPartiallyExpanded
    )
    if (managerViewModel.isBottomSheetOpen) {
        ManagerBottomSheet(modalBottomSheetState, scope, managerViewModel)
    }
}

@AllPreviews
@Composable
fun ExpandedManagerScreenPreview(
    @PreviewParameter(PreviewWidthSizeProvider::class) windowWidthSizeClass: WindowWidthSizeClass
) {
    ManagerScreen(windowWidthSizeClass, {}, {}, {})
}
