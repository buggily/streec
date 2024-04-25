package com.buggily.streec.feature.streecs.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buggily.streec.domain.streec.GetStreecById
import com.buggily.streec.domain.streec.StreecUi
import com.buggily.streec.domain.streec.UpdateStreecById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StreecEditViewModel @Inject constructor(
    private val getStreecById: GetStreecById,
    private val updateStreecById: UpdateStreecById,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val id: Long = checkNotNull(savedStateHandle["id"])

    private val _uiState: MutableStateFlow<StreecEditUiState>
    val uiState: StateFlow<StreecEditUiState> get() = _uiState

    private val _eventState: MutableSharedFlow<StreecEditEventState> = MutableSharedFlow()
    val eventState: SharedFlow<StreecEditEventState> get() = _eventState

    init {
        StreecEditUiState(
            nameState = StreecEditUiState.NameState(
                value = String(),
                onValueChange = ::onNameChange,
            ),
            confirmState = StreecEditUiState.ConfirmState(
                onClick = ::onConfirmClick,
            ),
        ).let { _uiState = MutableStateFlow(it) }

        viewModelScope.launch {
            val streec: StreecUi = getStreecById(id) ?: return@launch
            _uiState.update { it.copy(nameState = it.nameState.copy(value = streec.nameText)) }
        }
    }

    private fun onNameChange(name: String) = _uiState.update {
        it.copy(nameState = it.nameState.copy(value = name))
    }

    private fun onConfirmClick() = viewModelScope.launch {
        val uiState: StreecEditUiState = uiState.value
        val nameState: StreecEditUiState.NameState = uiState.nameState
        val name: String = nameState.value.trim()

        updateStreecById(
            id = id,
            name = name,
        )

        _eventState.emit(StreecEditEventState.OnConfirmClick)
    }
}
