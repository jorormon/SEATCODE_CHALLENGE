package com.jordiortuno.rover.framework.source.model


import com.jordiortuno.rover.data.model.RoverInstructionsData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RoverResponse(
    @SerialName("movements")
    val movements: String,
    @SerialName("roverDirection")
    val roverDirection: String,
    @SerialName("roverPosition")
    val roverPositionResponse: RoverPositionResponse,
    @SerialName("topRightCorner")
    val topRightCornerResponse: TopRightCornerResponse,
) {

    fun toData(): RoverInstructionsData {
        val movements = movements.toList()
        return RoverInstructionsData(
            movements = movements.mapNotNull {
                it.toData()
            },
            grid = RoverInstructionsData.GridData(
                topRightCornerResponse.x + 1,
                topRightCornerResponse.y + 1
            ),
            roverPosition = RoverInstructionsData.RoverPositionData(
                roverPositionResponse.x, roverPositionResponse.y, roverDirection.toData()
            )
        )
    }

    private fun String.toData(): RoverInstructionsData.RoverPositionData.DirectionData {
        return when (this) {
            "N" -> RoverInstructionsData.RoverPositionData.DirectionData.NORTH
            "S" -> RoverInstructionsData.RoverPositionData.DirectionData.SOUTH
            "E" -> RoverInstructionsData.RoverPositionData.DirectionData.EST
            "W" -> RoverInstructionsData.RoverPositionData.DirectionData.WEST
            else -> error("Illegal value $this")
        }
    }

    private fun Char.toData(): RoverInstructionsData.Movement? {
        return when (this) {
            'L' -> RoverInstructionsData.Movement.LEFT
            'R' -> RoverInstructionsData.Movement.RIGHT
            'M' -> RoverInstructionsData.Movement.MOVE
            else -> null
        }
    }
}