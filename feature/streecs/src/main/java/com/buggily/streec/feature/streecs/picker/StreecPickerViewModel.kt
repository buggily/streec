package com.buggily.streec.feature.streecs.picker

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buggily.streec.domain.streec.DeleteStreecById
import com.buggily.streec.domain.streec.ResetStreecById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StreecPickerViewModel @Inject constructor(
    private val resetStreecById: ResetStreecById,
    private val deleteStreecById: DeleteStreecById,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val id: Long = checkNotNull(savedStateHandle["id"])

    private val _uiState: MutableStateFlow<StreecPickerUiState>
    val uiState: StateFlow<StreecPickerUiState> get() = _uiState

    private val _eventState: MutableSharedFlow<StreecPickerEventState> = MutableSharedFlow()
    val eventState: SharedFlow<StreecPickerEventState> get() = _eventState

    init {
        StreecPickerUiState(
            onEditClick = ::onEditClick,
            onResetClick = ::onResetClick,
            onDeleteClick = ::onDeleteClick,
        ).let { _uiState = MutableStateFlow(it) }
    }

    private fun onEditClick() = viewModelScope.launch {
        _eventState.emit(StreecPickerEventState.OnEditClick(id))
    }

    private fun onResetClick() = viewModelScope.launch {
        resetStreecById(id)
        _eventState.emit(StreecPickerEventState.OnResetClick)
    }

    private fun onDeleteClick() = viewModelScope.launch {
        deleteStreecById(id)
        _eventState.emit(StreecPickerEventState.OnDeleteClick)
    }
}
