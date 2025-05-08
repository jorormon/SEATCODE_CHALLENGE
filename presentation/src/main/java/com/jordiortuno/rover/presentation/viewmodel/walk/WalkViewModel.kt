package com.jordiortuno.rover.presentation.viewmodel.walk

import androidx.lifecycle.viewModelScope
import com.jordiortuno.rover.domain.usecase.GetRoverInstructionsUseCase
import com.jordiortuno.rover.presentation.viewmodel.infra.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class WalkViewModel(private val getRoverInstructionsUseCase: GetRoverInstructionsUseCase) :
    ViewModel<WalkContract.Event, WalkContract.State, WalkContract.Effect>() {
    override fun createInitialState(): WalkContract.State {
        return WalkContract.State(true)
    }

    override fun handleEvent(event: WalkContract.Event) {
        when (event) {
            WalkContract.Event.OnInitialized -> {
                viewModelScope.launch {
                    getRoverInstructionsUseCase().fold({

                    }) {
                        setState {
                            this.copy(
                                uiModel = WalkContract.State.UiModel(
                                    Grid(it.grid.columns, it.grid.rows), GridPosition(
                                        it.roverPosition.x, it.roverPosition.y,
                                        it.roverPosition.direction.toUiModel()
                                    ),
                                    movements = it.movements.map { it.toUiModel() }
                                )
                            )
                        }
                    }
                }
            }

            WalkContract.Event.PlayMovement -> {
                viewModelScope.launch {
                    state.value.uiModel?.movements?.forEach { movement ->
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
                                        )
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

                        delay(1000)
                    }
                }


            }
        }
    }
}


