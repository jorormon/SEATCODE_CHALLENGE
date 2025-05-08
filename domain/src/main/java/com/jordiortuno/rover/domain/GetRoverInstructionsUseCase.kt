package com.jordiortuno.rover.domain

class GetRoverInstructionsUseCase : UseCase<Unit, String>() {
    override val block: suspend (param: Unit) -> String
        get() = {
            ""
        }
}