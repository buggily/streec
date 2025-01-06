package com.buggily.streec.feature.streecs.edit

import androidx.lifecycle.SavedStateHandle
import com.buggily.streec.core.test.CoroutineTestRule
import com.buggily.streec.domain.streec.GetStreecById
import com.buggily.streec.domain.streec.StreecUi
import com.buggily.streec.domain.streec.UpdateStreecById
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
class StreecEditViewModelTest {

    private val getStreecById: GetStreecById = mockk()
    private val updateStreecById: UpdateStreecById = mockk()
    private lateinit var viewModel: StreecEditViewModel

    @get:Rule
    val rule = CoroutineTestRule(StandardTestDispatcher())

    @Before
    fun before() {
        clearAllMocks()

        coEvery {
            getStreecById(any())
        } returns StreecUi(
            id = ID,
            nameText = NAME,
            streecText = String(),
        )

        viewModel = StreecEditViewModel(
            getStreecById = getStreecById,
            updateStreecById = updateStreecById,
            savedStateHandle = SavedStateHandle().apply { set(ID_KEY, ID) }
        )
    }

    @Test
    fun onInitNameStateNameIsName() = runTest {
        Assert.assertEquals(
            NAME,
            viewModel.uiState.value.nameState.value,
        )
    }

    @Test
    fun onNameChangeUpdatesNameStateName() = runTest {
        val name = "newname"
        viewModel.uiState.value.nameState.onValueChange(name)

        Assert.assertEquals(
            name,
            viewModel.uiState.value.nameState.value,
        )
    }

    @Test
    fun onConfirmClickEmitsOnConfirmClickEvent() = runTest {
        coEvery {
            updateStreecById(
                id = any(),
                name = any(),
            )
        } returns Unit

        viewModel.uiState.value.confirmState.onClick()

        Assert.assertEquals(
            StreecEditEventState.OnConfirmClick,
            viewModel.eventState.first(),
        )
    }

    @Test
    fun onConfirmClickUpdatesStreecById() = runTest {
        coEvery {
            updateStreecById(
                id = any(),
                name = any(),
            )
        } returns Unit

        viewModel.uiState.value.confirmState.onClick()
        runCurrent()

        coVerify {
            updateStreecById(
                id = ID,
                name = NAME,
            )
        }
    }

    private companion object {
        private const val ID_KEY = "id"
        private const val ID = 0L
        private const val NAME = "name"
    }
}
