package com.jordiortuno.rover.domain.usecase

import com.jordiortuno.rover.domain.infra.UseCase
import com.jordiortuno.rover.domain.model.RoverInstructions
import com.jordiortuno.rover.domain.repository.RoverRepository

class GetRoverInstructionsUseCase(private val repository: RoverRepository) :
    UseCase<Unit, RoverInstructions>() {
    override val block: suspend (param: Unit) -> RoverInstructions
        get() = {
            repository.getRoverInstructions()
        }
}