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
import com.tuxsnct.inkwell.model.Folder
import com.tuxsnct.inkwell.model.Note
import com.tuxsnct.inkwell.model.Template

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManagerBottomSheet(
    sheetState: SheetState,
    onDismissRequest: () -> Unit
) {
    val context = LocalContext.current

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FilledTonalButton(
                onClick = {
                    val note = Note.create(context, null)
                    println(note.file.absolutePath)
                },
            ) {
                Icon(Icons.Default.Book, contentDescription = "Note")
                Spacer(modifier = Modifier.width(16.dp))
                Text("Note")
            }
            FilledTonalButton(
                onClick = {
                    val template = Template.create(context, null)
                    println(template.file.absolutePath)
                },
            ) {
                Icon(Icons.Default.Description, contentDescription = "Template")
                Spacer(modifier = Modifier.width(16.dp))

                Text("Template")
            }
            FilledTonalButton(
                onClick = {
                    val folder = Folder.create(Note.getNotesDir(context), null, null)
                    println(folder.file.absolutePath)
                },
            ) {
                Icon(Icons.Default.Folder, contentDescription = "Folder")
                Spacer(modifier = Modifier.width(16.dp))
                Text("Folder")
            }
        }
    }
}