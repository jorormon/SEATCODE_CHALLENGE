package com.jordiortuno.rover.di

import org.koin.core.KoinApplication
import org.koin.dsl.module

fun KoinApplication.dataModule() = modules(dataSourceModule, repositoryModule)

private val dataSourceModule = module {
}

private val repositoryModule = module {


}