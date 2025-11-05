package com.buggily.streec.feature.streecs.picker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buggily.streec.feature.streecs.R
import com.buggily.streec.core.ui.R as CR

@Composable
fun StreecPickerDialog(
    viewModel: StreecPickerViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState: StreecPickerUiState by viewModel.uiState.collectAsStateWithLifecycle()

    StreecPickerDialog(
        uiState = uiState,
        modifier = modifier,
    )
}

@Composable
fun StreecPickerDialog(
    uiState: StreecPickerUiState,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier,
    ) {
        StreecPickerColumn(
            uiState = uiState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(CR.dimen.padding))
                .clip(MaterialTheme.shapes.medium),
        )
    }
}

@Composable
private fun StreecPickerColumn(
    uiState: StreecPickerUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = dimensionResource(CR.dimen.padding),
            alignment = Alignment.Top,
        ),
        horizontalAlignment = Alignment.Start,
        modifier = modifier,
    ) {
        StreecPickerButton(
            text = stringResource(R.string.streec_picker_edit),
            painter =  painterResource(CR.drawable.edit),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { uiState.onEditClick() }
                .padding(dimensionResource(CR.dimen.padding)),
        )

        StreecPickerButton(
            text = stringResource(R.string.streec_picker_reset),
            painter = painterResource(CR.drawable.reset),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { uiState.onResetClick() }
                .padding(dimensionResource(CR.dimen.padding)),
        )

        StreecPickerButton(
            text = stringResource(R.string.streec_picker_delete),
            painter = painterResource(CR.drawable.delete),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { uiState.onDeleteClick() }
                .padding(dimensionResource(CR.dimen.padding)),
        )
    }
}

@Composable
private fun StreecPickerButton(
    text: String,
    painter: Painter,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            space = dimensionResource(CR.dimen.padding),
            alignment = Alignment.Start,
        ),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Icon(
            painter = painter,
            contentDescription = text,
        )

        Text(
            text = text,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}
