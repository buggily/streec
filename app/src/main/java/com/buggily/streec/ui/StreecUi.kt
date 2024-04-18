package com.buggily.streec.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.buggily.streec.R

@Composable
fun StreecScreen(
    viewModel: StreecViewModel,
    modifier: Modifier,
) {
    Box(modifier) {
        Text(
            text = stringResource(R.string.greeting),
            modifier = Modifier.align(Alignment.Center),
        )
    }
}
