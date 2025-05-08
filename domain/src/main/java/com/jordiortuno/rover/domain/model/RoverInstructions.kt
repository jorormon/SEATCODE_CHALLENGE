package com.jordiortuno.rover.domain.model

data class RoverInstructions(
    val roverPosition: RoverPosition,
    val grid: Grid,
    val movements: List<Movement>,
) {
    data class RoverPosition(
        val x: Int,
        val y: Int,
        val direction: Direction,
    ) {
        enum class Direction {
            NORTH,
            WEST,
            SOUTH,
            EST;
        }
    }

    data class Grid(
        val columns: Int,
        val rows: Int,
    )

    enum class Movement{
        LEFT,
        RIGHT,
        MOVE;
    }

}