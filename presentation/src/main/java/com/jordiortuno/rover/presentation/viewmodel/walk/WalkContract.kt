package com.jordiortuno.rover.presentation.viewmodel.walk

import com.jordiortuno.rover.domain.model.RoverInstructions
import com.jordiortuno.rover.presentation.viewmodel.infra.UIEffect
import com.jordiortuno.rover.presentation.viewmodel.infra.UIEvent
import com.jordiortuno.rover.presentation.viewmodel.infra.UIState

interface WalkContract {
    data class State(
        val loading: Boolean = false,
        val uiModel: UiModel? = null,
    ) : UIState {
        data class UiModel(
            val grid: Grid,
            val roverPosition: RoverPosition,
            val movements: List<Movement>,
            val lastPosition: String? = null,
        )

    }

    sealed interface Event : UIEvent {
        data object OnInitialized : Event
        data object PlayMovement : Event
    }

    sealed interface Effect : UIEffect {
        sealed interface Navigation : Effect {
            data object NavigateStartRoverWalk : Navigation
        }
    }
}

data class RoverPosition(
    val x: Int,
    val y: Int,
    val direction: Direction,
) {
    fun toText(): String {
        return "$x $y ${direction.name.first()}"
    }
}

data class Grid(
    val columns: Int,
    val rows: Int,
) {
    val numberOfCells = columns * rows
}

enum class Direction {
    NORTH,
    WEST,
    SOUTH,
    EST
}

enum class Movement {
    LEFT,
    RIGHT,
    MOVE;
}

fun RoverInstructions.Movement.toUiModel(): Movement {
    return when (this) {
        RoverInstructions.Movement.LEFT -> Movement.LEFT
        RoverInstructions.Movement.RIGHT -> Movement.RIGHT
        RoverInstructions.Movement.MOVE -> Movement.MOVE
    }
}

fun RoverInstructions.RoverPosition.Direction.toUiModel(): Direction {
    return when (this) {
        RoverInstructions.RoverPosition.Direction.NORTH -> Direction.NORTH
        RoverInstructions.RoverPosition.Direction.WEST -> Direction.WEST
        RoverInstructions.RoverPosition.Direction.SOUTH -> Direction.SOUTH
        RoverInstructions.RoverPosition.Direction.EST -> Direction.EST
    }
}