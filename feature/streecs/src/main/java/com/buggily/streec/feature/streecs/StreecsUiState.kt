package com.buggily.streec.feature.streecs

import com.buggily.streec.domain.streec.StreecUi

data class StreecsUiState(
    val streec: StreecUi?,
    val onClick: (StreecUi) -> Unit,
    val onCreateClick: () -> Unit,
    val isCreateClicked: Boolean,
)
