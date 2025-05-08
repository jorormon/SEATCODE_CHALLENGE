package com.jordiortuno.roverapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.jordiortuno.rover.presentation.viewmodel.walk.Direction
import com.jordiortuno.rover.presentation.viewmodel.walk.Grid
import com.jordiortuno.rover.presentation.viewmodel.walk.Movement
import com.jordiortuno.rover.presentation.viewmodel.walk.RoverPosition
import com.jordiortuno.rover.presentation.viewmodel.walk.RoverState
import com.jordiortuno.rover.presentation.viewmodel.walk.WalkContract
import com.jordiortuno.roverapp.features.walk.WalkScreen
import kotlinx.coroutines.flow.emptyFlow
import org.junit.Rule
import org.junit.Test

class WalkScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testWalkScreenContent_StoppedCase() {
        val mockGrid = Grid(columns = 5, rows = 5)
        val mockRoverPosition = RoverPosition(x = 2, y = 2, direction = Direction.NORTH)
        val mockMovements = listOf(Movement.LEFT, Movement.MOVE)
        val mockUiModel = WalkContract.State.UiModel(
            grid = mockGrid,
            roverPosition = mockRoverPosition,
            movements = mockMovements,
            state = RoverState.STOPPED
        )

        val mockState = WalkContract.State(
            loading = false,
            uiModel = mockUiModel
        )

        composeTestRule.setContent {
            WalkScreen(
                state = mockState,
                effect = emptyFlow(),
                onEventSend = {},
                onNavigationRequested = {}
            )
        }

        composeTestRule.onNodeWithText("PLAY").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("rover").assertExists()
    }

    @Test
    fun testWalkScreenContent_PlayingCase() {
        val mockGrid = Grid(columns = 5, rows = 5)
        val mockRoverPosition = RoverPosition(x = 2, y = 2, direction = Direction.NORTH)
        val mockMovements = listOf(Movement.LEFT, Movement.MOVE)
        val mockUiModel = WalkContract.State.UiModel(
            grid = mockGrid,
            roverPosition = mockRoverPosition,
            movements = mockMovements,
            state = RoverState.PLAYING
        )

        val mockState = WalkContract.State(
            loading = false,
            uiModel = mockUiModel
        )

        composeTestRule.setContent {
            WalkScreen(
                state = mockState,
                effect = emptyFlow(),
                onEventSend = {},
                onNavigationRequested = {}
            )
        }

        composeTestRule.onNodeWithText("PAUSE").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("rover").assertExists()
    }

    @Test
    fun testWalkScreenContent_PausedCase() {
        val mockGrid = Grid(columns = 5, rows = 5)
        val mockRoverPosition = RoverPosition(x = 2, y = 2, direction = Direction.NORTH)
        val mockMovements = listOf(Movement.LEFT, Movement.MOVE)
        val mockUiModel = WalkContract.State.UiModel(
            grid = mockGrid,
            roverPosition = mockRoverPosition,
            movements = mockMovements,
            state = RoverState.PAUSED
        )

        val mockState = WalkContract.State(
            loading = false,
            uiModel = mockUiModel
        )

        // Use the compose test rule to set the content
        composeTestRule.setContent {
            WalkScreen(
                state = mockState,
                effect = emptyFlow(),
                onEventSend = {},
                onNavigationRequested = {}
            )
        }

        // Assertions to check the UI elements
        composeTestRule.onNodeWithText("CONTINUE").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("rover").assertExists()
    }

    @Test
    fun testWalkScreenContent_FinishedCase() {
        val mockGrid = Grid(columns = 5, rows = 5)
        val mockRoverPosition = RoverPosition(x = 2, y = 2, direction = Direction.NORTH)
        val mockMovements = listOf(Movement.LEFT, Movement.MOVE)
        val mockUiModel = WalkContract.State.UiModel(
            grid = mockGrid,
            roverPosition = mockRoverPosition,
            movements = mockMovements,
            state = RoverState.FINISHED
        )

        val mockState = WalkContract.State(
            loading = false,
            uiModel = mockUiModel
        )

        // Use the compose test rule to set the content
        composeTestRule.setContent {
            WalkScreen(
                state = mockState,
                effect = emptyFlow(),
                onEventSend = {},
                onNavigationRequested = {}
            )
        }

        // Assertions to check the UI elements
        composeTestRule.onNodeWithText("RESTART").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("rover").assertExists()
    }
}