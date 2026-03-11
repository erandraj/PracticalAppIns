package com.chickson.myapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PracticalAppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()


    @Test
    fun test1_mustBeAbleToStartTheApp() {
        composeTestRule.onNodeWithTag("LoginScreen").assertIsDisplayed()
        composeTestRule.onNodeWithTag("PasswordField").assertIsDisplayed()
        composeTestRule.onNodeWithTag("LoginButton").assertIsDisplayed()
    }

    @Test
    fun test2_mustBeAbleToKeyInPasswordAndPressLogin() {
        composeTestRule.onNodeWithTag("PasswordField").performTextInput("Test@2026")

        composeTestRule.onNodeWithTag("LoginButton").performClick()

        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithTag("OptionsScreen")
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithTag("OptionsScreen").assertIsDisplayed()
    }

    @Test
    fun test3_handleSecondPageLogicBasedOnText() {
        navigateToSecondPage()

        val textNodes = composeTestRule.onAllNodesWithText("test text 1", ignoreCase = true)
            .fetchSemanticsNodes()

        if (textNodes.isNotEmpty()) {
            composeTestRule.onNodeWithTag("TestTextButton").performClick()

            composeTestRule.waitUntil(timeoutMillis = 5000) {
                composeTestRule.onAllNodesWithTag("PinScreen")
                    .fetchSemanticsNodes().isNotEmpty()
            }
            composeTestRule.onNodeWithTag("PinScreen").assertIsDisplayed()
        } else {
            composeTestRule.onNodeWithTag("NotTestTextButton").performClick()
        }
    }

    @Test
    fun test4_thirdPageMustBeAbleToInputPin() {

        navigateToThirdPage()

        composeTestRule.onNodeWithTag("PinField").performTextInput("8526")

        composeTestRule.onNodeWithTag("PinField").assertTextContains("8526")
    }

    private fun navigateToSecondPage() {
        composeTestRule.onNodeWithTag("PasswordField").performTextInput("Test@2026")
        composeTestRule.onNodeWithTag("LoginButton").performClick()

        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithTag("OptionsScreen")
                .fetchSemanticsNodes().isNotEmpty()
        }
    }

    private fun navigateToThirdPage() {
        navigateToSecondPage()

        val textNodes = composeTestRule.onAllNodesWithText("test text 1", ignoreCase = true)
            .fetchSemanticsNodes()

        if (textNodes.isNotEmpty()) {
            composeTestRule.onNodeWithTag("TestTextButton").performClick()

            composeTestRule.waitUntil(timeoutMillis = 5000) {
                composeTestRule.onAllNodesWithTag("PinScreen")
                    .fetchSemanticsNodes().isNotEmpty()
            }
        } else {
            throw IllegalStateException("Cannot test page 3 because 'test text 1' is missing on page 2.")
        }
    }
}