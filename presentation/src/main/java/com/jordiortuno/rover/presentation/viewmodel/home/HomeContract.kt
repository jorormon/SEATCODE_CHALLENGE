package com.jordiortuno.rover.presentation.viewmodel.home

import com.jordiortuno.rover.presentation.viewmodel.infra.UIEffect
import com.jordiortuno.rover.presentation.viewmodel.infra.UIEvent
import com.jordiortuno.rover.presentation.viewmodel.infra.UIState

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