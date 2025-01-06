package com.buggily.streec.feature.streecs

import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.buggily.streec.domain.streec.StreecUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class StreecsUiTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun streecsScreenHasProgressIndicatorWhenStreecsLoading() {
        val streecsPaging: PagingData<StreecUi> = PagingData.empty(
            sourceLoadStates = LoadStates(
                refresh = LoadState.Loading,
                prepend = LoadState.Loading,
                append = LoadState.Loading,
            )
        )

        rule.setContent {
            val streecsFlow: Flow<PagingData<StreecUi>> = flowOf(streecsPaging)
            val streecs: LazyPagingItems<StreecUi> = streecsFlow.collectAsLazyPagingItems()

            StreecsScreen(
                streecs = streecs,
                uiState = uiState,
            )
        }

        rule.onNode(hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate)).assertIsDisplayed()
    }

    @Test
    fun streecsScreenHasZeroStateWhenStreecsIsEmpty() {
        val streecsPaging: PagingData<StreecUi> = PagingData.empty(
            sourceLoadStates = LoadStates(
                refresh = LoadState.NotLoading(endOfPaginationReached = true),
                prepend = LoadState.Loading,
                append = LoadState.Loading,
            )
        )

        rule.setContent {
            val streecsFlow: Flow<PagingData<StreecUi>> = flowOf(streecsPaging)
            val streecs: LazyPagingItems<StreecUi> = streecsFlow.collectAsLazyPagingItems()

            StreecsScreen(
                streecs = streecs,
                uiState = uiState,
            )
        }

        val zeroTag: String = rule.activity.getString(R.string.streecs_zero_tag)
        rule.onNodeWithTag(zeroTag).assertIsDisplayed()
    }

    @Test
    fun streecsScreenLacksZeroStateWhenStreecsIsNotEmpty() {
        val streec = StreecUi(
            id = 0,
            nameText = String(),
            streecText = String(),
        )

        val streecsPaging: PagingData<StreecUi> = PagingData.from(
            data = listOf(streec),
            sourceLoadStates = LoadStates(
                refresh = LoadState.NotLoading(endOfPaginationReached = true),
                prepend = LoadState.Loading,
                append = LoadState.Loading,
            )
        )

        rule.setContent {
            val streecsFlow: Flow<PagingData<StreecUi>> = flowOf(streecsPaging)
            val streecs: LazyPagingItems<StreecUi> = streecsFlow.collectAsLazyPagingItems()

            StreecsScreen(
                streecs = streecs,
                uiState = uiState,
            )
        }

        val zeroTag: String = rule.activity.getString(R.string.streecs_zero_tag)
        rule.onNodeWithTag(zeroTag).assertIsNotDisplayed()
    }

    @Test
    fun streecScreenZeroButtonClicks() {
        var isClick = false

        val uiState: StreecsUiState = uiState.copy(
            onCreateClick = { isClick = true },
        )

        val streecsPaging: PagingData<StreecUi> = PagingData.empty(
            sourceLoadStates = LoadStates(
                refresh = LoadState.NotLoading(endOfPaginationReached = true),
                prepend = LoadState.Loading,
                append = LoadState.Loading,
            )
        )

        rule.setContent {
            val streecsFlow: Flow<PagingData<StreecUi>> = flowOf(streecsPaging)
            val streecs: LazyPagingItems<StreecUi> = streecsFlow.collectAsLazyPagingItems()

            StreecsScreen(
                streecs = streecs,
                uiState = uiState,
            )
        }

        val zeroTag: String = rule.activity.getString(R.string.streecs_zero_tag)
        rule.onNodeWithTag(zeroTag).performClick()

        Assert.assertTrue(isClick)
    }

    @Test
    fun streecScreenCreateButtonClicks() {
        var isClick = false

        val uiState: StreecsUiState = uiState.copy(
            onCreateClick = { isClick = true },
        )

        val streecsPaging: PagingData<StreecUi> = PagingData.empty(
            sourceLoadStates = LoadStates(
                refresh = LoadState.Loading,
                prepend = LoadState.Loading,
                append = LoadState.Loading,
            )
        )

        rule.setContent {
            val streecsFlow: Flow<PagingData<StreecUi>> = flowOf(streecsPaging)
            val streecs: LazyPagingItems<StreecUi> = streecsFlow.collectAsLazyPagingItems()

            StreecsScreen(
                streecs = streecs,
                uiState = uiState,
            )
        }

        val createTag: String = rule.activity.getString(R.string.streecs_create_tag)
        rule.onNodeWithTag(createTag).performClick()

        Assert.assertTrue(isClick)
    }

    @Test
    fun streecScreenStreecClicks() {
        var itStreec: StreecUi? = null

        val uiState: StreecsUiState = uiState.copy(
            onClick = { itStreec = it },
        )

        val streec = StreecUi(
            id = 0,
            nameText = "nameText",
            streecText = "streecText",
        )

        val streecsPaging: PagingData<StreecUi> = PagingData.from(
            data = listOf(streec),
            sourceLoadStates = LoadStates(
                refresh = LoadState.NotLoading(endOfPaginationReached = true),
                prepend = LoadState.Loading,
                append = LoadState.Loading,
            )
        )

        rule.setContent {
            val streecsFlow: Flow<PagingData<StreecUi>> = flowOf(streecsPaging)
            val streecs: LazyPagingItems<StreecUi> = streecsFlow.collectAsLazyPagingItems()

            StreecsScreen(
                streecs = streecs,
                uiState = uiState,
            )
        }

        rule.onNodeWithText(streec.nameText).performClick()

        Assert.assertEquals(
            streec,
            itStreec,
        )
    }

    private val uiState = StreecsUiState(
        onClick = {},
        onCreateClick = {},
    )
}
