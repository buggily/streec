package com.buggily.streec.core.ui

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun StreecTheme(content: @Composable () -> Unit) {
    val context: Context = LocalContext.current
    val isLight: Boolean = !isSystemInDarkTheme()

    val colorScheme: ColorScheme = remember(isLight) {
        if (isLight) {
            lightColorSchemeCompat(context)
        } else {
            darkColorSchemeCompat(context)
        }
    }

    StreecTheme(
        colorScheme = colorScheme,
        content = content,
    )
}

@Composable
fun StreecTheme(
    colorScheme: ColorScheme,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}
