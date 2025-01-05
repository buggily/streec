package com.buggily.streec.feature.streecs.create

import com.buggily.streec.core.test.CoroutineTestRule
import com.buggily.streec.domain.streec.CreateStreec
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class StreecCreateViewModelTest {

    private val createStreec: CreateStreec = mockk()
    private lateinit var viewModel: StreecCreateViewModel

    @get:Rule
    val rule = CoroutineTestRule(StandardTestDispatcher())

    @Before
    fun before() {
        clearAllMocks()
        viewModel = StreecCreateViewModel(createStreec)
    }

    @Test
    fun `on name change updates name state name`() = runTest {
        viewModel.uiState.value.nameState.onValueChange(NAME)

        Assert.assertEquals(
            NAME,
            viewModel.uiState.value.nameState.value
        )
    }

    @Test
    fun `on confirm click emits on confirm click event`() = runTest {
        coEvery {
            createStreec(any())
        } returns Unit

        viewModel.uiState.value.confirmState.onClick()

        Assert.assertEquals(
            StreecCreateEventState.OnConfirmClick,
            viewModel.eventState.first(),
        )
    }

    @Test
    fun `on confirm click creates streec`() = runTest {
        coEvery {
            createStreec(any())
        } returns Unit

        viewModel.uiState.value.nameState.onValueChange(NAME)
        viewModel.uiState.value.confirmState.onClick()
        runCurrent()

        coVerify { createStreec(NAME) }
    }

    private companion object {
        private const val NAME = "name"
    }
}
