package com.jordiortuno.rover.data.repository

import com.jordiortuno.rover.data.sources.RoverRemoteDataSource
import com.jordiortuno.rover.domain.model.RoverInstructions
import com.jordiortuno.rover.domain.repository.RoverRepository

class DefaultRoverRepository(private val remoteDataSource: RoverRemoteDataSource) :
    RoverRepository {
    override suspend fun getRoverInstructions(): RoverInstructions {
        return remoteDataSource.getRoverInstructions().toDomain()
    }
}