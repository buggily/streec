package com.buggily.streec.feature.streecs.picker

data class StreecPickerUiState(
    val onEditClick: () -> Unit,
    val isEditClicked: Boolean,
    val onResetClick: () -> Unit,
    val isResetClicked: Boolean,
    val onDeleteClick: () -> Unit,
    val isDeleteClicked: Boolean,
)
