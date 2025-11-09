package com.buggily.streec.feature.streecs.picker

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buggily.streec.domain.streec.DeleteStreecById
import com.buggily.streec.domain.streec.ResetStreecById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StreecPickerViewModel @Inject constructor(
    private val resetStreecById: ResetStreecById,
    private val deleteStreecById: DeleteStreecById,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val id: Long = checkNotNull(savedStateHandle["id"])

    private val _uiState: MutableStateFlow<StreecPickerUiState>
    val uiState: StateFlow<StreecPickerUiState> get() = _uiState.asStateFlow()

    init {
        StreecPickerUiState(
            onEditClick = ::onEditClick,
            isEditClicked = false,
            onResetClick = ::onResetClick,
            isResetClicked = false,
            onDeleteClick = ::onDeleteClick,
            isDeleteClicked = false,
        ).let { _uiState = MutableStateFlow(it) }
    }

    private fun onEditClick() = _uiState.update {
        it.copy(isEditClicked = true)
    }

    fun onEditClicked() = _uiState.update {
        it.copy(isEditClicked = false)
    }

    private fun onResetClick() = viewModelScope.launch {
        resetStreecById(id)
        _uiState.update { it.copy(isResetClicked = true) }
    }

    fun onResetClicked() = _uiState.update {
        it.copy(isResetClicked = false)
    }

    private fun onDeleteClick() = viewModelScope.launch {
        deleteStreecById(id)
        _uiState.update { it.copy(isDeleteClicked = true) }
    }

    fun onDeleteClicked() = _uiState.update {
        it.copy(isDeleteClicked = false)
    }
}
