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
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.tuxsnct.inkwell.model.file.Folder
import com.tuxsnct.inkwell.model.file.Note
import com.tuxsnct.inkwell.utils.CompletePreviews
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManagerFileItem(
    name: String,
    navigateToEditor: () -> Unit
) {
    Card (
        modifier = Modifier
            .aspectRatio(1f)
            .semantics(mergeDescendants = true) {},
        onClick = navigateToEditor
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(name)
        }
    }
}

@Composable
fun ManagerFilesGrid(
    folders: List<Folder>,
    navigateToEditor: (Folder) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        contentPadding = PaddingValues(bottom = 8.dp, start = 8.dp, end = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(folders.size) {
            val folder = folders[it]
            ManagerFileItem("${folder.type} ${folder.file.name}") {
                navigateToEditor(folder)
            }
        }
    }
}

@CompletePreviews
@Composable
fun ManagerFilesGridPreview() {
    val files = (1..10).map {
        val file = File("$it")
        Note(file, Folder.getMetadataStore(file))
    }
    ManagerFilesGrid(files, {})
}