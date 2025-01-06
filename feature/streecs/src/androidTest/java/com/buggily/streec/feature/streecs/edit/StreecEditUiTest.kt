package com.buggily.streec.feature.streecs.edit

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.buggily.streec.core.ui.StreecTheme
import com.buggily.streec.feature.streecs.R
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class StreecEditUiTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun streecEditDialogHasTitle() {
        rule.setContent {
            StreecTheme {
                StreecEditDialog(uiState)
            }
        }

        val title: String = rule.activity.getString(R.string.streec_edit_title)
        rule.onNodeWithText(title).assertIsDisplayed()
    }

    @Test
    fun streecEditDialogHasNameTextField() {
        rule.setContent {
            StreecTheme {
                StreecEditDialog(uiState)
            }
        }

        val name: String = rule.activity.getString(R.string.streec_edit_name)
        rule.onNodeWithText(name).assertIsDisplayed()
    }

    @Test
    fun streecEditDialogNameTextFieldUpdates() {
        var text = String()
        val textLiteral = "text"

        val nameState = StreecEditUiState.NameState(
            value = text,
            onValueChange = { text = it },
        )

        val uiState: StreecEditUiState = uiState.copy(
            nameState = nameState,
        )

        rule.setContent {
            StreecTheme {
                StreecEditDialog(uiState)
            }
        }

        val name: String = rule.activity.getString(R.string.streec_edit_name)
        rule.onNodeWithText(name).performTextInput(textLiteral)

        Assert.assertEquals(
            textLiteral,
            text,
        )
    }

    @Test
    fun streecEditDialogHasConfirmButton() {
        rule.setContent {
            StreecTheme {
                StreecEditDialog(uiState)
            }
        }

        val confirm: String = rule.activity.getString(R.string.streec_edit_confirm)
        rule.onNodeWithText(confirm).assertIsDisplayed()
    }

    @Test
    fun streecEditDialogConfirmButtonIsDisabledWhenNameIsInvalid() {
        val nameState = StreecEditUiState.NameState(
            value = String(),
            onValueChange = {},
        )

        val uiState: StreecEditUiState = uiState.copy(
            nameState = nameState,
        )

        rule.setContent {
            StreecTheme {
                StreecEditDialog(uiState)
            }
        }

        val confirm: String = rule.activity.getString(R.string.streec_edit_confirm)
        rule.onNodeWithText(confirm).assertIsNotEnabled()
    }

    @Test
    fun streecEditDialogConfirmButtonIsEnabledWhenNameIsValid() {
        val nameState = StreecEditUiState.NameState(
            value = "name",
            onValueChange = {},
        )

        val uiState: StreecEditUiState = uiState.copy(
            nameState = nameState,
        )

        rule.setContent {
            StreecTheme {
                StreecEditDialog(uiState)
            }
        }

        val confirm: String = rule.activity.getString(R.string.streec_edit_confirm)
        rule.onNodeWithText(confirm).assertIsEnabled()
    }

    @Test
    fun streecEditDialogConfirmButtonClicks() {
        var isClick = false

        val nameState = StreecEditUiState.NameState(
            value = "name",
            onValueChange = {},
        )

        val confirmState = StreecEditUiState.ConfirmState(
            onClick = { isClick = true },
        )

        val uiState: StreecEditUiState = uiState.copy(
            nameState = nameState,
            confirmState = confirmState,
        )

        rule.setContent {
            StreecTheme {
                StreecEditDialog(uiState)
            }
        }

        val confirm: String = rule.activity.getString(R.string.streec_edit_confirm)
        rule.onNodeWithText(confirm).performClick()

        Assert.assertTrue(isClick)
    }

    private val uiState = StreecEditUiState(
        nameState = StreecEditUiState.NameState(
            value = String(),
            onValueChange = {},
        ),
        confirmState = StreecEditUiState.ConfirmState(
            onClick = {},
        )
    )
}
