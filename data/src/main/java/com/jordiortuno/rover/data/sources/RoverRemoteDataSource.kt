package com.jordiortuno.rover.data.sources

import com.jordiortuno.rover.data.model.RoverInstructionsData

interface RoverRemoteDataSource {

    suspend fun getRoverInstructions(): RoverInstructionsData
}