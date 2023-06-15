package com.tuxsnct.inkwell.ui.components.editor

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.tuxsnct.inkwell.utils.AllPreviews
import com.tuxsnct.inkwell.utils.PreviewWidthSizeProvider

@Composable
fun ExpandableIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    label: @Composable () -> Unit,
    widthSizeClass: WindowWidthSizeClass,
    isActive: Boolean
) {
    Button(
        modifier = modifier
            .animateContentSize()
            .sizeIn(minWidth = ButtonDefaults.MinHeight, minHeight = ButtonDefaults.MinHeight),
        onClick = { onClick() },
        shape = CircleShape,
        colors = if (isActive) ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) else ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        contentPadding = if (isActive) ButtonDefaults.ContentPadding else PaddingValues(0.dp)
    ) {
        icon()
        if (widthSizeClass == WindowWidthSizeClass.Expanded && isActive) {
            Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))
            label()
        }
    }
}

@AllPreviews
@Composable
fun IconButtonPreview(
    @PreviewParameter(PreviewWidthSizeProvider::class) widthSizeClass: WindowWidthSizeClass
) {
    ExpandableIconButton({}, icon = { Icon(Icons.Default.Menu, "Menu") }, label = { Text("Menu") }, widthSizeClass = widthSizeClass, isActive = true)
}