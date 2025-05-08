package com.jordiortuno.rover.di


import org.koin.core.KoinApplication
import org.koin.dsl.module

fun KoinApplication.domainModule() = modules(useCasesModule)

private val useCasesModule = module {
}