package com.buggily.streec.feature.streecs.picker

data class StreecPickerUiState(
    val onEditClick: () -> Unit,
    val onResetClick: () -> Unit,
    val onDeleteClick: () -> Unit,
)
