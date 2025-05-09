package com.jordiortuno.rover.presentation.viewmodel.home

import com.jordiortuno.rover.presentation.viewmodel.infra.ViewModel


class HomeViewModel : ViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {
    override fun createInitialState(): HomeContract.State {
        return HomeContract.State
    }

    override fun handleEvent(event: HomeContract.Event) {
        when (event) {
            HomeContract.Event.OnClickStart -> {
                setEffect {
                    HomeContract.Effect.Navigation.NavigateStartRoverWalk
                }
            }
        }
    }
}


