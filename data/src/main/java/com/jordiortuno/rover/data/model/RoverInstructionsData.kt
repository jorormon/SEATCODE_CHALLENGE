package com.jordiortuno.rover.data.model

import com.jordiortuno.rover.domain.model.RoverInstructions

data class RoverInstructionsData(
    val roverPosition: RoverPositionData,
    val grid: GridData,
    val movements: List<Movement>,
) {
    fun toDomain(): RoverInstructions {
        return RoverInstructions(
            roverPosition = roverPosition.toDomain(),
            grid = grid.toDomain(),
            movements = movements.map { it.toDomain() }
        )
    }

    data class RoverPositionData(
        val x: Int,
        val y: Int,
        val direction: DirectionData,
    ) {
        enum class DirectionData {
            NORTH,
            WEST,
            SOUTH,
            EST;

            fun toDomain(): RoverInstructions.RoverPosition.Direction {
                return when (this) {
                    NORTH -> RoverInstructions.RoverPosition.Direction.NORTH
                    WEST -> RoverInstructions.RoverPosition.Direction.WEST
                    SOUTH -> RoverInstructions.RoverPosition.Direction.SOUTH
                    EST -> RoverInstructions.RoverPosition.Direction.EST
                }
            }
        }

        fun toDomain(): RoverInstructions.RoverPosition {
            return RoverInstructions.RoverPosition(
                x, y, direction.toDomain()
            )
        }
    }

    data class GridData(
        val columns: Int,
        val rows: Int,
    ) {
        fun toDomain(): RoverInstructions.Grid {
            return RoverInstructions.Grid(columns, rows)
        }
    }

    enum class Movement {
        LEFT,
        RIGHT,
        MOVE;

        fun toDomain(): RoverInstructions.Movement {
            return when (this) {
                LEFT -> RoverInstructions.Movement.LEFT
                RIGHT -> RoverInstructions.Movement.RIGHT
                MOVE -> RoverInstructions.Movement.MOVE
            }
        }
    }

}