package com.buggily.streec.feature.streecs.create

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

class StreecCreateUiTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun streecCreateDialogHasTitle() {
        rule.setContent {
            StreecTheme {
                StreecCreateDialog(uiState)
            }
        }

        val title: String = rule.activity.getString(R.string.streec_create_title)
        rule.onNodeWithText(title).assertIsDisplayed()
    }

    @Test
    fun streecCreateDialogHasNameTextField() {
        rule.setContent {
            StreecTheme {
                StreecCreateDialog(uiState)
            }
        }

        val name: String = rule.activity.getString(R.string.streec_create_name)
        rule.onNodeWithText(name).assertIsDisplayed()
    }

    @Test
    fun streecCreateDialogNameTextFieldUpdates() {
        var text = String()
        val textLiteral = "text"

        val nameState = StreecCreateUiState.NameState(
            value = text,
            onValueChange = { text = it },
        )

        val uiState: StreecCreateUiState = uiState.copy(
            nameState = nameState,
        )

        rule.setContent {
            StreecTheme {
                StreecCreateDialog(uiState)
            }
        }

        val name: String = rule.activity.getString(R.string.streec_create_name)
        rule.onNodeWithText(name).performTextInput(textLiteral)

        Assert.assertEquals(
            textLiteral,
            text
        )
    }

    @Test
    fun streecCreateDialogHasConfirmButton() {
        rule.setContent {
            StreecTheme {
                StreecCreateDialog(uiState)
            }
        }

        val confirm: String = rule.activity.getString(R.string.streec_create_confirm)
        rule.onNodeWithText(confirm).assertIsDisplayed()
    }

    @Test
    fun streecCreateDialogConfirmButtonIsDisabledWhenNameIsInvalid() {
        val nameState = StreecCreateUiState.NameState(
            value = String(),
            onValueChange = {},
        )

        val uiState: StreecCreateUiState = uiState.copy(
            nameState = nameState,
        )

        rule.setContent {
            StreecTheme {
                StreecCreateDialog(uiState)
            }
        }

        val confirm: String = rule.activity.getString(R.string.streec_create_confirm)
        rule.onNodeWithText(confirm).assertIsNotEnabled()
    }

    @Test
    fun streecCreateDialogConfirmButtonIsEnabledWhenNameIsValid() {
        val nameState = StreecCreateUiState.NameState(
            value = "name",
            onValueChange = {},
        )

        val uiState: StreecCreateUiState = uiState.copy(
            nameState = nameState,
        )

        rule.setContent {
            StreecTheme {
                StreecCreateDialog(uiState)
            }
        }

        val confirm: String = rule.activity.getString(R.string.streec_create_confirm)
        rule.onNodeWithText(confirm).assertIsEnabled()
    }

    @Test
    fun streecCreateDialogConfirmButtonClicks() {
        var isClick = false

        val nameState = StreecCreateUiState.NameState(
            value = "name",
            onValueChange = {},
        )

        val confirmState = StreecCreateUiState.ConfirmState(
            onClick = { isClick = true },
        )

        val uiState: StreecCreateUiState = uiState.copy(
            nameState = nameState,
            confirmState = confirmState,
        )

        rule.setContent {
            StreecTheme {
                StreecCreateDialog(uiState)
            }
        }

        val confirm: String = rule.activity.getString(R.string.streec_create_confirm)
        rule.onNodeWithText(confirm).performClick()

        Assert.assertTrue(isClick)
    }

    private val uiState = StreecCreateUiState(
        nameState = StreecCreateUiState.NameState(
            value = String(),
            onValueChange = {},
        ),
        confirmState = StreecCreateUiState.ConfirmState(
            onClick = {},
        ),
    )
}
