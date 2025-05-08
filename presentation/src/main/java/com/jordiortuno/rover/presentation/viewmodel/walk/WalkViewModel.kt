package com.jordiortuno.rover.presentation.viewmodel.walk

import com.jordiortuno.rover.presentation.viewmodel.infra.UIEffect
import com.jordiortuno.rover.presentation.viewmodel.infra.UIEvent
import com.jordiortuno.rover.presentation.viewmodel.infra.UIState
import com.jordiortuno.rover.presentation.viewmodel.infra.ViewModel


class WalkViewModel : ViewModel<WalkContract.Event, WalkContract.State, WalkContract.Effect>() {
    override fun createInitialState(): WalkContract.State {
        return WalkContract.State(true)
    }

    override fun handleEvent(event: WalkContract.Event) {
        when (event) {
            WalkContract.Event.OnInitialized -> {
                setState {
                    this.copy(
                        uiModel = WalkContract.State.UiModel(Grid(5, 5), GridPosition(1, 2,
                            Direction.NORTH
                        ))
                    )
                }
            }
        }
    }
}


interface WalkContract {
    data class State(
        val loading: Boolean = false,
        val uiModel: UiModel? = null,
    ) : UIState {
        data class UiModel(val grid: Grid, val position: GridPosition)
    }

    sealed interface Event : UIEvent {
        data object OnInitialized : Event
    }

    sealed interface Effect : UIEffect {
        sealed interface Navigation : Effect {
            data object NavigateStartRoverWalk : Navigation
        }
    }
}

data class GridPosition(
    val x: Int,
    val y: Int,
    val direction: Direction
)

data class Grid(
    val columns: Int,
    val rows: Int,
) {
    val numberOfCells = columns * rows
}

enum class Direction{
    NORTH,
    WEST,
    SOUTH,
    EST
}