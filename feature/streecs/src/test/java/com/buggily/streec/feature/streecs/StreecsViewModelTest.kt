package com.buggily.streec.feature.streecs

import com.buggily.streec.core.test.CoroutineTestRule
import com.buggily.streec.domain.streec.GetStreecPaging
import com.buggily.streec.domain.streec.StreecUi
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

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
    fun `on click emits on click event`() = runTest {
        val streec = StreecUi(
            id = 0,
            nameText = String(),
            streecText = String(),
        )

        viewModel.uiState.value.onClick(streec)

        Assert.assertEquals(
            StreecsEventState.OnClick(streec),
            viewModel.eventState.first()
        )
    }

    @Test
    fun `on create click emits on create click event`() = runTest {
        viewModel.uiState.value.onCreateClick()

        Assert.assertEquals(
            StreecsEventState.OnCreateClick,
            viewModel.eventState.first()
        )
    }
}
