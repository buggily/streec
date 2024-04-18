package com.buggily.skeleton.ui.theme

import android.content.Context
import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme

fun lightColorSchemeCompat(context: Context): ColorScheme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
    dynamicLightColorScheme(context)
} else {
    lightColorScheme()
}

fun darkColorSchemeCompat(context: Context): ColorScheme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
    dynamicDarkColorScheme(context)
} else {
    darkColorScheme()
}
