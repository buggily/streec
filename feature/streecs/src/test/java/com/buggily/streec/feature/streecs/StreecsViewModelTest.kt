package com.buggily.streec.feature.streecs

import com.buggily.streec.core.test.CoroutineTestRule
import com.buggily.streec.domain.streec.GetStreecPaging
import com.buggily.streec.domain.streec.StreecUi
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class StreecsViewModelTest {

    private val getStreecPaging: GetStreecPaging = mockk()
    private lateinit var viewModel: StreecsViewModel

    @get:Rule
    val rule = CoroutineTestRule(StandardTestDispatcher())

    @Before
    fun before() {
        clearAllMocks()

        every { getStreecPaging() } returns emptyFlow()
        viewModel = StreecsViewModel(getStreecPaging)
    }

    @Test
    fun onClickSetsStreec() = runTest {
        val streec = StreecUi(
            id = 0,
            nameText = String(),
            streecText = String(),
        )

        viewModel.uiState.value.onClick(streec)
        runCurrent()

        Assert.assertEquals(
            streec,
            viewModel.uiState.value.streec,
        )
    }

    @Test
    fun onCreateClickSetsIsCreateClicked() = runTest {
        viewModel.uiState.value.onCreateClick()
        runCurrent()

        Assert.assertTrue(viewModel.uiState.value.isCreateClicked)
    }
}
