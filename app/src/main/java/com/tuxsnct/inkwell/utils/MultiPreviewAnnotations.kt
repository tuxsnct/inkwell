package com.tuxsnct.inkwell.utils

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "small font",
    group = "font scales",
    fontScale = 0.5f
)
@Preview(
    name = "large font",
    group = "font scales",
    fontScale = 1.5f
)
annotation class FontScalePreviews

@Preview(
    name = "phone",
    group = "devices",
    device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480"
)
annotation class CompactPreview

@Preview(
    name = "foldable",
    group = "devices",
    device = "spec:shape=Normal,width=673,height=841,unit=dp,dpi=480"
)
annotation class MediumPreview

@Preview(
    name = "tablet",
    group = "devices",
    device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480"
)
annotation class ExpandedPreview

@CompactPreview
@MediumPreview
@ExpandedPreview
annotation class DevicePreviews

@Preview(
    name = "dark theme",
    group = "themes",
    uiMode = UI_MODE_NIGHT_YES
)
annotation class DarkThemePreviews

@DarkThemePreviews
@FontScalePreviews
@DevicePreviews
annotation class AllPreviews
