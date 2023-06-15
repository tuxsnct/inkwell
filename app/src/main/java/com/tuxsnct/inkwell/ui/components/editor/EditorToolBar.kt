package com.tuxsnct.inkwell.ui.components.editor

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.tuxsnct.inkwell.R
import com.tuxsnct.inkwell.ui.renderer.ToolType
import com.tuxsnct.inkwell.utils.AllPreviews
import com.tuxsnct.inkwell.utils.PreviewWidthSizeProvider

@Composable
fun EditorToolBar(
    modifier: Modifier = Modifier,
    widthSizeClass: WindowWidthSizeClass,
    title: String,
    toolType: ToolType,
    onToolTypeButtonClick: (ToolType) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.inversePrimary.copy(alpha = 0.1f))
            .padding(horizontal = 4.dp)
            .clickable(interactionSource = interactionSource, indication = null) { },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ExpandableIconButton(
                {},
                icon = { Icon(Icons.Default.Menu, "Menu") },
                label = { Text("Menu")},
                widthSizeClass = widthSizeClass,
                isActive = true
            )
            if (widthSizeClass != WindowWidthSizeClass.Compact) {
                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = RoundedCornerShape(50.dp)
                        )
                        .padding(8.dp)
                ) {
                    Text(
                        "$title",
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(4.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ExpandableIconButton(
                { onToolTypeButtonClick(ToolType.PEN) },
                icon = { Icon(painter = painterResource(R.drawable.ink_pen), "Pen") },
                label = { Text("Pen") },
                widthSizeClass = widthSizeClass,
                isActive = toolType == ToolType.PEN
            )
            ExpandableIconButton(
                { onToolTypeButtonClick(ToolType.HIGHLIGHTER) },
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.ink_highlighter),
                        "Highlighter"
                    )
                },
                label = { Text("Highlighter") },
                widthSizeClass = widthSizeClass,
                isActive = toolType == ToolType.HIGHLIGHTER
            )
            ExpandableIconButton(
                { onToolTypeButtonClick(ToolType.ERASER) },
                icon = { Icon(painter = painterResource(R.drawable.ink_eraser), "Eraser") },
                label = { Text("Eraser") },
                widthSizeClass = widthSizeClass,
                isActive = toolType == ToolType.ERASER
            )
        }
    }
}

@AllPreviews
@Composable
fun EditorAppBarPreview(
    @PreviewParameter(PreviewWidthSizeProvider::class) widthSizeClass: WindowWidthSizeClass
) {
    EditorToolBar(widthSizeClass = widthSizeClass, title = "Editor", toolType = ToolType.PEN, onToolTypeButtonClick = {})
}