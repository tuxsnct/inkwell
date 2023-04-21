package com.tuxsnct.inkwell.ui.components.manager

import androidx.compose.runtime.Composable
import com.tuxsnct.inkwell.ui.components.common.ResponsiveNavigationItem

class ManagerTabItem(
    icon: @Composable () -> Unit,
    label: String,
    route: String
) : ResponsiveNavigationItem(icon, label, route)