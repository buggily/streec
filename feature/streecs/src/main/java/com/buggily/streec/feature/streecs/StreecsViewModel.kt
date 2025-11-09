package com.buggily.streec.feature.streecs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.buggily.streec.domain.streec.GetStreecPaging
import com.buggily.streec.domain.streec.StreecUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class StreecsViewModel @Inject constructor(
    getStreecPaging: GetStreecPaging,
) : ViewModel() {

    val streecs: Flow<PagingData<StreecUi>> = getStreecPaging().cachedIn(viewModelScope)

    private val _uiState: MutableStateFlow<StreecsUiState>
    val uiState: StateFlow<StreecsUiState> get() = _uiState.asStateFlow()

    init {
        StreecsUiState(
            streec = null,
            onClick = ::onClick,
            onCreateClick = ::onCreateClick,
            isCreateClicked = false,
        ).let { _uiState = MutableStateFlow(it) }
    }

    private fun onClick(streec: StreecUi) = _uiState.update {
        it.copy(streec = streec)
    }

    private fun onCreateClick() = _uiState.update {
        it.copy(isCreateClicked = true)
    }

    fun onClicked() = _uiState.update {
        it.copy(streec = null)
    }

    fun onCreateClicked() = _uiState.update {
        it.copy(isCreateClicked = false)
    }
}
