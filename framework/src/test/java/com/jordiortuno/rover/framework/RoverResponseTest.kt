package com.jordiortuno.rover.framework

import com.jordiortuno.rover.data.model.RoverInstructionsData
import com.jordiortuno.rover.framework.source.model.RoverPositionResponse
import com.jordiortuno.rover.framework.source.model.RoverResponse
import com.jordiortuno.rover.framework.source.model.TopRightCornerResponse
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.fail
import org.junit.Test

class RoverResponseTest {

    @Test
    fun `test toData converts RoverResponse to RoverInstructionsData correctly`() {
        val roverResponse = RoverResponse(
            movements = "LRM",
            roverDirection = "N",
            roverPositionResponse = RoverPositionResponse(1, 2),
            topRightCornerResponse = TopRightCornerResponse(4, 4)
        )

        val roverInstructionsData = roverResponse.toData()

        assertEquals(3, roverInstructionsData.movements.size)
        assertEquals(RoverInstructionsData.Movement.LEFT, roverInstructionsData.movements[0])
        assertEquals(RoverInstructionsData.Movement.RIGHT, roverInstructionsData.movements[1])
        assertEquals(RoverInstructionsData.Movement.MOVE, roverInstructionsData.movements[2])

        assertEquals(5, roverInstructionsData.grid.columns)
        assertEquals(5, roverInstructionsData.grid.rows)

        assertEquals(1, roverInstructionsData.roverPosition.x)
        assertEquals(2, roverInstructionsData.roverPosition.y)
        assertEquals(RoverInstructionsData.RoverPositionData.DirectionData.NORTH, roverInstructionsData.roverPosition.direction)
    }

    @Test
    fun `test toData converts RoverResponse with South to RoverInstructionsData correctly`() {
        val roverResponse = RoverResponse(
            movements = "LRM",
            roverDirection = "S",
            roverPositionResponse = RoverPositionResponse(1, 2),
            topRightCornerResponse = TopRightCornerResponse(4, 4)
        )

        val roverInstructionsData = roverResponse.toData()

        assertEquals(RoverInstructionsData.RoverPositionData.DirectionData.SOUTH, roverInstructionsData.roverPosition.direction)
    }

    @Test
    fun `test toData converts RoverResponse with EAST to RoverInstructionsData correctly`() {
        val roverResponse = RoverResponse(
            movements = "LRM",
            roverDirection = "E",
            roverPositionResponse = RoverPositionResponse(1, 2),
            topRightCornerResponse = TopRightCornerResponse(4, 4)
        )

        val roverInstructionsData = roverResponse.toData()

        assertEquals(RoverInstructionsData.RoverPositionData.DirectionData.EST, roverInstructionsData.roverPosition.direction)
    }

    @Test
    fun `test toData converts RoverResponse with WEST to RoverInstructionsData correctly`() {
        val roverResponse = RoverResponse(
            movements = "LRM",
            roverDirection = "W",
            roverPositionResponse = RoverPositionResponse(1, 2),
            topRightCornerResponse = TopRightCornerResponse(4, 4)
        )

        val roverInstructionsData = roverResponse.toData()

        assertEquals(RoverInstructionsData.RoverPositionData.DirectionData.WEST, roverInstructionsData.roverPosition.direction)
    }

    @Test
    fun `test toData with invalid direction throws error`() {
        val roverResponse = RoverResponse(
            movements = "LRM",
            roverDirection = "X",
            roverPositionResponse = RoverPositionResponse(1, 2),
            topRightCornerResponse = TopRightCornerResponse(4, 4)
        )

        try {
            roverResponse.toData()
            fail("Expected an IllegalArgumentException to be thrown")
        } catch (e: IllegalStateException) {
            assertEquals("Illegal value X", e.message)
        }
    }
}


