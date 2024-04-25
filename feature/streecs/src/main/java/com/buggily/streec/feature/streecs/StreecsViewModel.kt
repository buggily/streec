package com.buggily.streec.feature.streecs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.buggily.streec.domain.streec.GetStreecPaging
import com.buggily.streec.domain.streec.StreecUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StreecsViewModel @Inject constructor(
    getStreecPaging: GetStreecPaging,
) : ViewModel() {

    val streecs: Flow<PagingData<StreecUi>> = getStreecPaging().cachedIn(viewModelScope)

    private val _uiState: MutableStateFlow<StreecsUiState>
    val uiState: StateFlow<StreecsUiState> get() = _uiState

    private val _eventState: MutableSharedFlow<StreecsEventState> = MutableSharedFlow()
    val eventState: SharedFlow<StreecsEventState> get() = _eventState

    init {
        StreecsUiState(
            onClick = ::onClick,
            onCreateClick = ::onCreateClick,
        ).let { _uiState = MutableStateFlow(it) }
    }

    private fun onClick(streec: StreecUi) = viewModelScope.launch {
        _eventState.emit(StreecsEventState.OnClick(streec))
    }

    private fun onCreateClick() = viewModelScope.launch {
        _eventState.emit(StreecsEventState.OnCreateClick)
    }
}
