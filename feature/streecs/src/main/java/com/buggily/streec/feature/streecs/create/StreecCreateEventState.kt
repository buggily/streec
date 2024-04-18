package com.buggily.streec.feature.streecs.create

sealed interface StreecCreateEventState {
    data object OnConfirmClick : StreecCreateEventState
}
