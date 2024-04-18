package com.buggily.streec.feature.streecs.edit

data class StreecEditUiState(
    val nameState: NameState,
    val confirmState: ConfirmState,
) {

    data class NameState(
        val value: String,
        val onValueChange: (String) -> Unit,
    ) {

        val isValid: Boolean
            get() = value.isNotBlank()
    }

    data class ConfirmState(
        val onClick: () -> Unit,
    )
}
