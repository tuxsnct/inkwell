package com.tuxsnct.inkwell.ui.components.manager

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.tuxsnct.inkwell.utils.CompletePreviews

@Composable
fun ManagerFloatingActionButton(
    openAddMenu: () -> Unit
) {
    ExtendedFloatingActionButton(
        text = { Text("Add") },
        icon = { Icon(Icons.Default.Add, contentDescription = "Add") },
        onClick = { openAddMenu() }
    )
}

@CompletePreviews
@Composable
fun ManagerFloatingActionButtonPreview() {
    ManagerFloatingActionButton {}
}