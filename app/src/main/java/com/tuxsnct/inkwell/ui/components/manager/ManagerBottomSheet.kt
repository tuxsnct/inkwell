package com.tuxsnct.inkwell.ui.components.manager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.tuxsnct.inkwell.model.file.Collection
import com.tuxsnct.inkwell.model.file.Note
import com.tuxsnct.inkwell.model.file.Template
import com.tuxsnct.inkwell.ui.viewmodels.ManagerViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManagerBottomSheet(
    sheetState: SheetState,
    scope: CoroutineScope,
    managerViewModel: ManagerViewModel
) {
    val context = LocalContext.current

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { managerViewModel.closeBottomSheet() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FilledTonalButton(
                onClick = {
                    scope.launch {
                        Note.create(Note.getDir(context))
                        sheetState.hide()
                    }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            managerViewModel.closeBottomSheet()
                        }
                    }
                },
            ) {
                Icon(Icons.Default.Book, contentDescription = "Note")
                Spacer(modifier = Modifier.width(16.dp))
                Text("Note")
            }
            FilledTonalButton(
                onClick = {
                    scope.launch {
                        Template.create(Template.getDir(context))
                        sheetState.hide()
                    }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            managerViewModel.closeBottomSheet()
                        }
                    }
                },
            ) {
                Icon(Icons.Default.Description, contentDescription = "Template")
                Spacer(modifier = Modifier.width(16.dp))

                Text("Template")
            }
            FilledTonalButton(
                onClick = {
                    scope.launch {
                        Collection.create(context, Note.getDir(context))
                        sheetState.hide()
                    }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            managerViewModel.closeBottomSheet()
                        }
                    }
                },
            ) {
                Icon(Icons.Default.Folder, contentDescription = "Collection")
                Spacer(modifier = Modifier.width(16.dp))
                Text("Collection")
            }
        }
    }
}