package com.buggily.streec.feature.streecs.picker

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.buggily.streec.core.ui.StreecTheme
import com.buggily.streec.feature.streecs.R
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class StreecPickerUiTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun streecPickerDialogHasEditButton() {
        rule.setContent {
            StreecTheme {
                StreecPickerDialog(uiState)
            }
        }

        val edit: String = rule.activity.getString(R.string.streec_picker_edit)
        rule.onNodeWithText(edit).assertIsDisplayed()
    }

    @Test
    fun streecPickerDialogEditButtonClicks() {
        var isClick = false

        val uiState: StreecPickerUiState = uiState.copy(
            onEditClick = { isClick = true },
        )

        rule.setContent {
            StreecTheme {
                StreecPickerDialog(uiState)
            }
        }

        val edit: String = rule.activity.getString(R.string.streec_picker_edit)
        rule.onNodeWithText(edit).performClick()

        Assert.assertTrue(isClick)
    }

    @Test
    fun streecPickerDialogHasResetButton() {
        rule.setContent {
            StreecTheme {
                StreecPickerDialog(uiState)
            }
        }

        val reset: String = rule.activity.getString(R.string.streec_picker_delete)
        rule.onNodeWithText(reset).assertIsDisplayed()
    }

    @Test
    fun streecPickerDialogResetButtonClicks() {
        var isClick = false

        val uiState: StreecPickerUiState = uiState.copy(
            onResetClick = { isClick = true },
        )

        rule.setContent {
            StreecTheme {
                StreecPickerDialog(uiState)
            }
        }

        val reset: String = rule.activity.getString(R.string.streec_picker_reset)
        rule.onNodeWithText(reset).performClick()

        Assert.assertTrue(isClick)
    }

    @Test
    fun streecPickerDialogHasDeleteButton() {
        rule.setContent {
            StreecTheme {
                StreecPickerDialog(uiState)
            }
        }

        val delete: String = rule.activity.getString(R.string.streec_picker_delete)
        rule.onNodeWithText(delete).assertIsDisplayed()
    }

    @Test
    fun streecPickerDialogDeleteButtonClicks() {
        var isClick = false

        val uiState: StreecPickerUiState = uiState.copy(
            onDeleteClick = { isClick = true },
        )

        rule.setContent {
            StreecTheme {
                StreecPickerDialog(uiState)
            }
        }

        val delete: String = rule.activity.getString(R.string.streec_picker_delete)
        rule.onNodeWithText(delete).performClick()

        Assert.assertTrue(isClick)
    }

    private val uiState = StreecPickerUiState(
        onEditClick = {},
        onResetClick = {},
        onDeleteClick = {},
    )
}
