package com.tuxsnct.inkwell.ui.components.search

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.tuxsnct.inkwell.utils.AllPreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    onClickBackIcon: () -> Unit
) {
    LargeTopAppBar(
        title = { Text("Search") },
        navigationIcon = {
            IconButton(onClickBackIcon) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@AllPreviews
@Composable
fun SearchAppBarPreview() {
    SearchAppBar { }
}