package com.tuxsnct.inkwell.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.tuxsnct.inkwell.ui.components.search.SearchAppBar
import com.tuxsnct.inkwell.ui.viewmodels.SearchViewModel
import com.tuxsnct.inkwell.utils.CompletePreviews

@Composable
fun SearchScreen(
    isCompact: Boolean,
    onClickBack: () -> Unit
) {
    val searchViewModel: SearchViewModel = hiltViewModel()

    Scaffold(
        topBar = { SearchAppBar { onClickBack() } }
    ) { contentPadding ->
        Box(modifier = Modifier
            .padding(contentPadding)
            .fillMaxSize()) {
            Text("Search", modifier = Modifier.align(Alignment.Center))
        }
    }
}

@CompletePreviews
@Composable
fun CompactSearchScreenPreview() {
    SearchScreen(true) {}
}

@CompletePreviews
@Composable
fun ExpandedSearchScreenPreview() {
    SearchScreen(false) {}
}