package com.jordiortuno.rover.di

import com.jordiortuno.rover.presentation.viewmodel.home.HomeViewModel
import com.jordiortuno.rover.presentation.viewmodel.walk.WalkViewModel
import org.koin.core.KoinApplication
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun KoinApplication.presentationModule() = modules(presentationModule)

private val presentationModule = module {

    viewModelOf(::HomeViewModel)
    viewModelOf(::WalkViewModel)
}