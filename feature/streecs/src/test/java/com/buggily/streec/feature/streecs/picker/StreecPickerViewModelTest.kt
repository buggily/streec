package com.buggily.streec.feature.streecs.picker

import androidx.lifecycle.SavedStateHandle
import com.buggily.streec.core.test.CoroutineTestRule
import com.buggily.streec.domain.streec.DeleteStreecById
import com.buggily.streec.domain.streec.ResetStreecById
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
class StreecPickerViewModelTest {

    private val resetStreecById: ResetStreecById = mockk()
    private val deleteStreecById: DeleteStreecById = mockk()
    private lateinit var viewModel: StreecPickerViewModel

    @get:Rule
    val test = CoroutineTestRule(StandardTestDispatcher())

    @Before
    fun before() {
        clearAllMocks()

        viewModel = StreecPickerViewModel(
            resetStreecById = resetStreecById,
            deleteStreecById = deleteStreecById,
            savedStateHandle = SavedStateHandle().apply { set(ID_KEY, ID) },
        )
    }

    @Test
    fun `on edit click emits on edit click event`() = runTest {
        viewModel.uiState.value.onEditClick()

        Assert.assertEquals(
            StreecPickerEventState.OnEditClick(ID),
            viewModel.eventState.first(),
        )
    }

    @Test
    fun `on reset click emits on reset click event`() = runTest {
        coEvery {
            resetStreecById(any())
        } returns Unit

        viewModel.uiState.value.onResetClick()

        Assert.assertEquals(
            StreecPickerEventState.OnResetClick,
            viewModel.eventState.first(),
        )
    }

    @Test
    fun `on reset click resets streec by id`() = runTest {
        coEvery {
            resetStreecById(any())
        } returns Unit

        viewModel.uiState.value.onResetClick()
        runCurrent()

        coVerify { resetStreecById(ID) }
    }

    @Test
    fun `on delete click emits on delete click event`() = runTest {
        coEvery {
            deleteStreecById(any())
        } returns Unit

        viewModel.uiState.value.onDeleteClick()

        Assert.assertEquals(
            StreecPickerEventState.OnDeleteClick,
            viewModel.eventState.first(),
        )
    }

    @Test
    fun `on delete click deletes streec by id`() = runTest {
        coEvery {
            deleteStreecById(any())
        } returns Unit

        viewModel.uiState.value.onDeleteClick()
        runCurrent()

        coVerify { deleteStreecById(ID) }
    }

    private companion object {
        private const val ID_KEY = "id"
        private const val ID = 0L
    }
}
