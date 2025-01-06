package com.buggily.streec.feature.streecs.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.buggily.streec.feature.streecs.R
import com.buggily.streec.core.ui.R as CR

@Composable
fun StreecEditDialog(
    viewModel: StreecEditViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState: StreecEditUiState by viewModel.uiState.collectAsStateWithLifecycle()

    StreecEditDialog(
        uiState = uiState,
        modifier = modifier,
    )
}

@Composable
fun StreecEditDialog(
    uiState: StreecEditUiState,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = MaterialTheme.shapes.large,
        modifier = modifier,
    ) {
        StreecEditDialog(
            nameState = uiState.nameState,
            confirmState = uiState.confirmState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(CR.dimen.padding)),
        )
    }
}

@Composable
private fun StreecEditDialog(
    nameState: StreecEditUiState.NameState,
    confirmState: StreecEditUiState.ConfirmState,
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
        StreecEditHeader()

        StreecEditTextField(
            nameState = nameState,
            modifier = Modifier.fillMaxWidth(),
        )

        StreecEditConfirmButton(
            nameState = nameState,
            confirmState = confirmState,
            modifier = Modifier.align(Alignment.End),
        )
    }
}

@Composable
private fun StreecEditHeader(
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(R.string.streec_edit_title),
        textAlign = TextAlign.Start,
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier,
    )
}

@Composable
private fun StreecEditTextField(
    nameState: StreecEditUiState.NameState,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = nameState.value,
        onValueChange = nameState.onValueChange,
        label = { Text(stringResource(R.string.streec_edit_name)) },
        singleLine = true,
        modifier = modifier,
    )
}

@Composable
private fun StreecEditConfirmButton(
    nameState: StreecEditUiState.NameState,
    confirmState: StreecEditUiState.ConfirmState,
    modifier: Modifier = Modifier,
) {
    TextButton(
        enabled = nameState.isValid,
        onClick = confirmState.onClick,
        modifier = modifier,
    ) { Text(stringResource(R.string.streec_edit_confirm)) }
}
