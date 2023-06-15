package com.tuxsnct.inkwell.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.tuxsnct.inkwell.ui.navigations.RootNavGraph
import com.tuxsnct.inkwell.ui.theme.InkWellTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setContent {
            InkWellTheme {
                RootNavGraph(calculateWindowSizeClass(this).widthSizeClass)
            }
        }
    }

    fun openNewIntent() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT or
            Intent.FLAG_ACTIVITY_NEW_TASK or
            Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        startActivity(intent)
    }

    fun openLicenseIntent() {
        startActivity(Intent(this, OssLicensesMenuActivity::class.java))
    }
}
