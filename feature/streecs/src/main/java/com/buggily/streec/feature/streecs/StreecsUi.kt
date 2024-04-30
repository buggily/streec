package com.buggily.streec.feature.streecs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.buggily.streec.domain.streec.StreecUi
import com.buggily.streec.core.ui.R as CR

@Composable
fun StreecsScreen(
    viewModel: StreecsViewModel,
    modifier: Modifier = Modifier,
) {
    val streecs: LazyPagingItems<StreecUi> = viewModel.streecs.collectAsLazyPagingItems()
    val uiState: StreecsUiState by viewModel.uiState.collectAsStateWithLifecycle()

    StreecsScreen(
        streecs = streecs,
        uiState = uiState,
        modifier = modifier,
    )
}

@Composable
fun StreecsScreen(
    streecs: LazyPagingItems<StreecUi>,
    uiState: StreecsUiState,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier,
    ) {
        Box(modifier) {
            StreecsContent(
                streecs = streecs,
                uiState = uiState,
                modifier = Modifier.fillMaxSize(),
            )

            FloatingActionButton(
                onClick = uiState.onCreateClick,
                modifier = Modifier
                    .safeContentPadding()
                    .align(Alignment.BottomEnd),
            ) {
                Icon(
                    imageVector = Icons.Rounded.Create,
                    contentDescription = Icons.Rounded.Create.name,
                )
            }
        }
    }
}

@Composable
private fun StreecsContent(
    streecs: LazyPagingItems<StreecUi>,
    uiState: StreecsUiState,
    modifier: Modifier = Modifier,
) {
    val isZero: Boolean = remember(streecs.itemCount) {
        streecs.itemCount == 0
    }

    Box(modifier) {
        when (streecs.loadState.refresh) {
            is LoadState.Loading -> CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
            )

            else -> {
                AnimatedVisibility(visible = isZero) {
                    StreecsZero(
                        uiState = uiState,
                        modifier = Modifier.fillMaxSize(),
                    )
                }

                AnimatedVisibility(visible = !isZero) {
                    StreecsColumn(
                        streecs = streecs,
                        uiState = uiState,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun StreecsColumn(
    streecs: LazyPagingItems<StreecUi>,
    uiState: StreecsUiState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(
            space = dimensionResource(CR.dimen.padding),
            alignment = Alignment.Top,
        ),
        horizontalAlignment = Alignment.Start,
        contentPadding = WindowInsets.statusBars.asPaddingValues(),
        modifier = modifier,
    ) {
        stickyHeader {
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.fillMaxWidth(),
            ) {
                StreecRow(
                    nameText = stringResource(R.string.streecs_name),
                    streecText = stringResource(R.string.streecs_streec),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(CR.dimen.padding)),
                )
            }
        }

        items(
            count = streecs.itemCount,
            key = streecs.itemKey { it.id },
        ) {
            when (val streec: StreecUi? = streecs[it]) {
                is StreecUi -> StreecRow(
                    streec = streec,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { uiState.onClick(streec) }
                        .padding(dimensionResource(CR.dimen.padding)),
                )

                else -> Unit
            }
        }
    }
}

@Composable
private fun StreecRow(
    streec: StreecUi,
    modifier: Modifier = Modifier,
) {
    StreecRow(
        nameText = streec.nameText,
        streecText = streec.streecText,
        modifier = modifier,
    )
}

@Composable
private fun StreecRow(
    nameText: String,
    streecText: String,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            space = dimensionResource(CR.dimen.padding),
            alignment = Alignment.Start,
        ),
        verticalAlignment = Alignment.Top,
        modifier = modifier,
    ) {
        Text(
            text = nameText,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f),
        )

        Text(
            text = streecText,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.alpha(0.75f),
        )
    }
}

@Composable
private fun StreecsZero(
    uiState: StreecsUiState,
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        OutlinedButton(
            onClick = uiState.onCreateClick,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(dimensionResource(CR.dimen.padding)),
        ) {
            Text(
                text = stringResource(R.string.streecs_zero),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
            )
        }
    }
}
