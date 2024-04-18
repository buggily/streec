package com.buggily.streec.feature.streecs

import com.buggily.streec.domain.streec.StreecUi

sealed interface StreecsEventState {
    data class OnClick(val streec: StreecUi) : StreecsEventState
    data object OnCreateClick: StreecsEventState
}
