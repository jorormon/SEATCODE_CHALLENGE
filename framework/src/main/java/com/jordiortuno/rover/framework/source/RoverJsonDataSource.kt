package com.jordiortuno.rover.framework.source

import com.jordiortuno.rover.data.model.RoverInstructionsData
import com.jordiortuno.rover.data.sources.RoverRemoteDataSource

class RoverJsonDataSource : RoverRemoteDataSource {
    override suspend fun getRoverInstructions(): RoverInstructionsData {
        return RoverInstructionsData(
            RoverInstructionsData.RoverPositionData(
                2,
                2,
                RoverInstructionsData.RoverPositionData.DirectionData.NORTH
            ),
            RoverInstructionsData.GridData(6, 6),
            listOf(
                RoverInstructionsData.Movement.RIGHT,
                RoverInstructionsData.Movement.MOVE,
                RoverInstructionsData.Movement.MOVE,
                RoverInstructionsData.Movement.MOVE,
                RoverInstructionsData.Movement.MOVE,
                RoverInstructionsData.Movement.MOVE,
                RoverInstructionsData.Movement.MOVE,
                RoverInstructionsData.Movement.MOVE,
                RoverInstructionsData.Movement.MOVE,
                RoverInstructionsData.Movement.MOVE,
                RoverInstructionsData.Movement.MOVE,
            )
        )
    }
}