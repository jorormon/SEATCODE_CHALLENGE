package com.jordiortuno.rover.di

import android.content.Context
import com.jordiortuno.rover.data.repository.DefaultRoverRepository
import com.jordiortuno.rover.data.sources.RoverRemoteDataSource
import com.jordiortuno.rover.domain.repository.RoverRepository
import com.jordiortuno.rover.framework.source.RoverJsonDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.binds
import org.koin.dsl.module

fun KoinApplication.dataModule() = modules(dataSourceModule, repositoryModule)

private val dataSourceModule = module {
    single<RoverRemoteDataSource> { RoverJsonDataSource(androidContext()) }
}

private val repositoryModule = module {
    factory { DefaultRoverRepository(get()) } binds arrayOf(
        RoverRepository::class
    )
}