package com.jordiortuno.rover.presentation.viewmodel.walk

import androidx.lifecycle.viewModelScope
import com.jordiortuno.rover.domain.usecase.GetRoverInstructionsUseCase
import com.jordiortuno.rover.presentation.viewmodel.infra.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex


class WalkViewModel(private val getRoverInstructionsUseCase: GetRoverInstructionsUseCase) :
    ViewModel<WalkContract.Event, WalkContract.State, WalkContract.Effect>() {
    override fun createInitialState(): WalkContract.State {
        return WalkContract.State(true)
    }

    private lateinit var startPositionRover: RoverPosition

    private var movementsRemaining: MutableList<Movement> = mutableListOf()

    private val mutex: Mutex = Mutex()

    override fun handleEvent(event: WalkContract.Event) {
        when (event) {
            WalkContract.Event.OnInitialized -> onInitialized()

            WalkContract.Event.PlayMovement -> {
                onPlayMovement()
            }

            WalkContract.Event.ContinueMovement -> onPlayMovement(true)
            WalkContract.Event.PauseMovement -> setState {
                this.copy(
                    uiModel = this.uiModel?.copy(
                        state = RoverState.PAUSED,
                    )
                )
            }

            WalkContract.Event.ResetRover -> {
                resetRoverPosition()
            }
        }
    }

    private fun onInitialized() {
        viewModelScope.launch {
            getRoverInstructionsUseCase().fold({}) { roverInstructions ->
                val initPosition = RoverPosition(
                    roverInstructions.roverPosition.x, roverInstructions.roverPosition.y,
                    roverInstructions.roverPosition.direction.toUiModel()
                )
                startPositionRover = initPosition
                setState {
                    this.copy(
                        loading = false,
                        uiModel = WalkContract.State.UiModel(
                            Grid(roverInstructions.grid.columns, roverInstructions.grid.rows),
                            initPosition,
                            movements = roverInstructions.movements.map { it.toUiModel() },
                        )
                    )
                }
            }
        }
    }

    private fun onPlayMovement(onContinue: Boolean = false) {
        viewModelScope.launch {
            mutex.lock()
            setState {
                this.copy(
                    uiModel = this.uiModel?.copy(
                        state = RoverState.PLAYING,
                    )
                )
            }
            if (!onContinue) movementsRemaining =
                state.value.uiModel?.movements?.toMutableList() ?: mutableListOf()

            val iterator = movementsRemaining.iterator()
            while (iterator.hasNext()) {
                val movement = iterator.next()

                if (state.value.uiModel?.state == RoverState.PAUSED) {
                    mutex.unlock()
                    return@launch
                }

                doMovement(movement)
                iterator.remove()
                delay(1000)
            }

            setState {
                this.copy(
                    uiModel = this.uiModel?.copy(
                        lastPosition = this.uiModel.roverPosition.toText(),
                        state = RoverState.FINISHED,
                    )
                )
            }
            mutex.unlock()
        }
    }

    private fun resetRoverPosition() {
        viewModelScope.launch {
            setState {
                this.copy(
                    uiModel = this.uiModel?.copy(
                        lastPosition = null,
                        roverPosition = startPositionRover,
                        state = RoverState.STOPPED
                    )
                )
            }
        }
    }

    private fun doMovement(movement: Movement) {
        when (movement) {
            Movement.LEFT -> setState {
                this.copy(
                    uiModel = this.uiModel?.copy(
                        roverPosition = this.uiModel.roverPosition.copy(
                            direction = when (this.uiModel.roverPosition.direction) {
                                Direction.NORTH -> Direction.WEST
                                Direction.WEST -> Direction.SOUTH
                                Direction.SOUTH -> Direction.EST
                                Direction.EST -> Direction.NORTH
                            }
                        ),
                    )
                )
            }

            Movement.RIGHT -> setState {
                this.copy(
                    uiModel = this.uiModel?.copy(
                        roverPosition = this.uiModel.roverPosition.copy(
                            direction = when (this.uiModel.roverPosition.direction) {
                                Direction.NORTH -> Direction.EST
                                Direction.EST -> Direction.SOUTH
                                Direction.SOUTH -> Direction.WEST
                                Direction.WEST -> Direction.NORTH
                            }
                        )
                    )
                )
            }

            Movement.MOVE -> setState {
                this.copy(
                    uiModel = this.uiModel?.copy(
                        roverPosition = this.uiModel.roverPosition.copy(
                            x = when {
                                this.uiModel.roverPosition.direction == Direction.EST && this.uiModel.roverPosition.x < this.uiModel.grid.columns - 1 -> {
                                    this.uiModel.roverPosition.x + 1
                                }

                                this.uiModel.roverPosition.direction == Direction.WEST && this.uiModel.roverPosition.x > 0 -> {
                                    this.uiModel.roverPosition.x - 1
                                }

                                else -> {
                                    this.uiModel.roverPosition.x
                                }
                            },
                            y = when {
                                this.uiModel.roverPosition.direction == Direction.NORTH && this.uiModel.roverPosition.y < this.uiModel.grid.rows - 1 -> {
                                    this.uiModel.roverPosition.y + 1
                                }

                                this.uiModel.roverPosition.direction == Direction.SOUTH && this.uiModel.roverPosition.y > 0 -> {
                                    this.uiModel.roverPosition.y - 1
                                }

                                else -> {
                                    this.uiModel.roverPosition.y
                                }
                            }
                        )
                    )
                )
            }
        }
    }
}


