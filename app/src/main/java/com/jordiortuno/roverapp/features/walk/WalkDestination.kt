package com.jordiortuno.roverapp.features.walk

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.jordiortuno.rover.presentation.viewmodel.walk.WalkContract
import com.jordiortuno.rover.presentation.viewmodel.walk.WalkViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WalkDestination(navController: NavController) {
    val vm = koinViewModel<WalkViewModel>()
    val state: WalkContract.State by vm.state.collectAsState()

    LaunchedEffect(key1 = "INITIAL") {
        vm.setEvent(WalkContract.Event.OnInitialized)
    }

    WalkScreen(
        state = state,
        effect = vm.effect,
        onEventSend = { vm.setEvent(it) },
        onNavigationRequested = {
            when (it) {
                WalkContract.Effect.Navigation.NavigateStartRoverWalk -> navController.navigate(
                    WalkRoute
                )
            }
        }
    )
}