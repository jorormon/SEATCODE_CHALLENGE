package com.jordiortuno.rover.domain.repository

import com.jordiortuno.rover.domain.model.RoverInstructions

interface RoverRepository {

    suspend fun getRoverInstructions(): RoverInstructions
}