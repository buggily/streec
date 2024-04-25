package com.buggily.streec.feature.streecs.edit

sealed interface StreecEditEventState {
    data object OnConfirmClick : StreecEditEventState
}
