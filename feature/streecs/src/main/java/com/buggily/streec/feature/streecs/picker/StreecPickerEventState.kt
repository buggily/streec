package com.buggily.streec.feature.streecs.picker

sealed interface StreecPickerEventState {

    data class OnEditClick(
        val id: Long,
    ) : StreecPickerEventState

    data object OnResetClick : StreecPickerEventState
    data object OnDeleteClick : StreecPickerEventState
}
