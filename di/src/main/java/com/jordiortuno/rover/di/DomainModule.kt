package com.jordiortuno.rover.di


import com.jordiortuno.rover.domain.usecase.GetRoverInstructionsUseCase
import org.koin.core.KoinApplication
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

fun KoinApplication.domainModule() = modules(useCasesModule)

private val useCasesModule = module {
    factoryOf(::GetRoverInstructionsUseCase)
}