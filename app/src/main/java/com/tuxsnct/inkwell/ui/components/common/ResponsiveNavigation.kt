package com.tuxsnct.inkwell.ui.components.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy

enum class ResponsiveNavigationType {
    NavigationBar,
    NavigationRail
}

open class ResponsiveNavigationItem (
    val icon: @Composable () -> Unit,
    val label: String,
    val route: String
)

@Composable
fun ResponsiveNavigation(
    type: ResponsiveNavigationType,
    tabs: List<ResponsiveNavigationItem>,
    currentDestination: NavDestination?,
    onTabClick: (String) -> Unit
) {
    when (type) {
        ResponsiveNavigationType.NavigationBar -> {
            NavigationBar {
                tabs.forEach { tab ->
                    NavigationBarItem(
                        icon = tab.icon,
                        label = { Text(tab.label) },
                        selected = currentDestination?.hierarchy?.any { it.route == tab.route } == true,
                        onClick = { onTabClick(tab.route) }
                    )
                }
            }
        }
        ResponsiveNavigationType.NavigationRail -> {
            NavigationRail (modifier = Modifier.padding(top = 8.dp)) {
                tabs.forEach { tab ->
                    NavigationRailItem(
                        icon = tab.icon,
                        label = { Text(tab.label) },
                        selected = currentDestination?.hierarchy?.any { it.route == tab.route } == true,
                        onClick = { onTabClick(tab.route) }
                    )
                }
            }
        }
    }
}