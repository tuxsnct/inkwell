package com.tuxsnct.inkwell.ui.components.manager

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.tuxsnct.inkwell.model.AppSpecificFile
import com.tuxsnct.inkwell.model.Note
import com.tuxsnct.inkwell.utils.CompletePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManagerFileItem(
    name: String,
    navigateToEditor: () -> Unit
) {
    Card (
        modifier = Modifier.aspectRatio(1f).semantics(mergeDescendants = true) {},
        onClick = navigateToEditor
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(name)
        }
    }
}

@Composable
fun ManagerFilesGrid(
    files: List<AppSpecificFile>,
    navigateToEditor: (AppSpecificFile) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        contentPadding = PaddingValues(bottom = 8.dp, start = 8.dp, end = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(files.size) {
            val file = files[it]
            ManagerFileItem("${file.type} ${file.name}") { navigateToEditor(files[it]) }
        }
    }
}

@CompletePreviews
@Composable
fun ManagerFilesGridPreview() {
    val files = (1..10).map {
        Note(LocalContext.current.filesDir, "$it")
    }
    ManagerFilesGrid(files) {}
}