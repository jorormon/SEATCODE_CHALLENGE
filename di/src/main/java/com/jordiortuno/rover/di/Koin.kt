package com.jordiortuno.rover.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    appDeclaration: KoinAppDeclaration = {},
) =
    startKoin {
        appDeclaration()
        dataModule()
        domainModule()
        presentationModule()
    }
