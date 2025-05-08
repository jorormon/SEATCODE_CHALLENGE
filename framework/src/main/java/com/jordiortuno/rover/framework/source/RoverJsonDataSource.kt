package com.jordiortuno.rover.framework.source

import com.jordiortuno.rover.data.model.RoverInstructionsData
import com.jordiortuno.rover.data.sources.RoverRemoteDataSource

class RoverJsonDataSource : RoverRemoteDataSource {
    override suspend fun getRoverInstructions(): RoverInstructionsData {
        TODO("Not yet implemented")
    }
}