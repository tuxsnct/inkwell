package com.tuxsnct.inkwell.ui.components.editor

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.tuxsnct.inkwell.utils.CompletePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorAppBar(
    title: String,
    onClickBack: () -> Unit
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClickBack) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@CompletePreviews
@Composable
fun EditorAppBarPreview() {
    EditorAppBar("Editor") {}
}