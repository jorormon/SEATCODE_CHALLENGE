package com.jordiortuno.rover.presentation

import com.jordiortuno.rover.domain.infra.success
import com.jordiortuno.rover.domain.model.RoverInstructions
import com.jordiortuno.rover.domain.usecase.GetRoverInstructionsUseCase
import com.jordiortuno.rover.presentation.viewmodel.walk.Direction
import com.jordiortuno.rover.presentation.viewmodel.walk.RoverPosition
import com.jordiortuno.rover.presentation.viewmodel.walk.RoverState
import com.jordiortuno.rover.presentation.viewmodel.walk.WalkContract
import com.jordiortuno.rover.presentation.viewmodel.walk.WalkViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class WalkViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: WalkViewModel
    private lateinit var mockGetRoverInstructionsUseCase: GetRoverInstructionsUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockGetRoverInstructionsUseCase = mock(GetRoverInstructionsUseCase::class.java)
        viewModel = WalkViewModel(mockGetRoverInstructionsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test onInitialized sets initial state correctly`() = runTest {
        val mockInstructions = RoverInstructions(
            grid = RoverInstructions.Grid(5, 5),
            roverPosition = RoverInstructions.RoverPosition(
                0,
                0,
                RoverInstructions.RoverPosition.Direction.NORTH
            ),
            movements = listOf(RoverInstructions.Movement.LEFT, RoverInstructions.Movement.RIGHT)
        )

        `when`(mockGetRoverInstructionsUseCase.invoke()).thenReturn(mockInstructions.success())

        viewModel.handleEvent(WalkContract.Event.OnInitialized)

        val state = viewModel.state.first {
            !it.loading
        }

        assertEquals(false, state.loading)
        assertEquals(0, state.uiModel?.roverPosition?.x)
        assertEquals(0, state.uiModel?.roverPosition?.y)
        assertEquals(
            RoverInstructions.RoverPosition.Direction.NORTH.name,
            state.uiModel?.roverPosition?.direction?.name
        )
    }


    @Test
    fun `test play movement starts rover`() = runTest {
        val mockInstructions = RoverInstructions(
            grid = RoverInstructions.Grid(5, 5),
            roverPosition = RoverInstructions.RoverPosition(
                0,
                0,
                RoverInstructions.RoverPosition.Direction.NORTH
            ),
            movements = listOf(RoverInstructions.Movement.MOVE)
        )

        `when`(mockGetRoverInstructionsUseCase.invoke()).thenReturn(mockInstructions.success())

        viewModel.handleEvent(WalkContract.Event.OnInitialized)
        advanceUntilIdle()
        viewModel.handleEvent(WalkContract.Event.PlayMovement)
        advanceUntilIdle()

        val state = viewModel.state.first()

        assertEquals(RoverState.FINISHED, state.uiModel?.state)
    }

    @Test
    fun `test reset rover returns to start position`() = runTest {
        val startPosition = RoverPosition(0, 0, Direction.NORTH)
        val mockInstructions = RoverInstructions(
            grid = RoverInstructions.Grid(5, 5),
            roverPosition = RoverInstructions.RoverPosition(
                0,
                0,
                RoverInstructions.RoverPosition.Direction.NORTH
            ),
            movements = listOf(RoverInstructions.Movement.LEFT, RoverInstructions.Movement.RIGHT)
        )

        `when`(mockGetRoverInstructionsUseCase.invoke()).thenReturn(mockInstructions.success())
        viewModel.handleEvent(WalkContract.Event.OnInitialized)
        advanceUntilIdle()

        viewModel.handleEvent(WalkContract.Event.ResetRover)

        val state = viewModel.state.first()

        assertEquals(startPosition, state.uiModel?.roverPosition)
        assertEquals(RoverState.STOPPED, state.uiModel?.state)
    }
}