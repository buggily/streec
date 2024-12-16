package com.buggily.streec.core.ext

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

@Composable
fun PaddingValues.coerceAtLeast(padding: Dp): PaddingValues {
    val layoutDirection: LayoutDirection = LocalLayoutDirection.current

    return PaddingValues(
        start = calculateStartPadding(layoutDirection).coerceAtLeast(padding),
        top = calculateTopPadding().coerceAtLeast(padding),
        end = calculateEndPadding(layoutDirection).coerceAtLeast(padding),
        bottom = calculateBottomPadding().coerceAtLeast(padding),
    )
}
