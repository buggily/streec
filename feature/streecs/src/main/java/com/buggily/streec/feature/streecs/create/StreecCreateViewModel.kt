package com.buggily.streec.feature.streecs.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buggily.streec.domain.streec.CreateStreec
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StreecCreateViewModel @Inject constructor(
    private val createStreec: CreateStreec,
) : ViewModel() {

    private val _uiState: MutableStateFlow<StreecCreateUiState>
    val uiState: StateFlow<StreecCreateUiState> get() = _uiState

    private val _eventState: MutableSharedFlow<StreecCreateEventState> = MutableSharedFlow()
    val eventState: SharedFlow<StreecCreateEventState> get() = _eventState

    init {
        StreecCreateUiState(
            nameState = StreecCreateUiState.NameState(
                value = String(),
                onValueChange = ::onNameChange,
            ),
            confirmState = StreecCreateUiState.ConfirmState(
                onClick = ::onConfirmClick,
            ),
        ).let { _uiState = MutableStateFlow(it) }
    }

    private fun onNameChange(name: String) = _uiState.update {
        it.copy(nameState = it.nameState.copy(value = name))
    }

    private fun onConfirmClick() = viewModelScope.launch {
        val uiState: StreecCreateUiState = uiState.value
        val nameState: StreecCreateUiState.NameState = uiState.nameState
        val name: String = nameState.value.trim()

        createStreec(name = name)
        _eventState.emit(StreecCreateEventState.OnConfirmClick)
    }
}
