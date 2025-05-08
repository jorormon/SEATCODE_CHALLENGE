package com.jordiortuno.rover.presentation.viewmodel.home

import com.jordiortuno.rover.presentation.viewmodel.infra.UIEffect
import com.jordiortuno.rover.presentation.viewmodel.infra.UIEvent
import com.jordiortuno.rover.presentation.viewmodel.infra.UIState
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


interface HomeContract {

    data object State: UIState

    sealed interface Event : UIEvent {
        data object OnClickStart : Event
    }

    sealed interface Effect : UIEffect {
        sealed interface Navigation : Effect {
            data object NavigateStartRoverWalk : Navigation
        }
    }
}