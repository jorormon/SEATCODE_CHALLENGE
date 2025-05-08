package com.jordiortuno.roverapp.features.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.jordiortuno.rover.presentation.viewmodel.home.HomeContract
import com.jordiortuno.rover.presentation.viewmodel.home.HomeViewModel
import com.jordiortuno.roverapp.features.walk.WalkRoute
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeDestination(
    navController: NavController,
) {
    val vm = koinViewModel<HomeViewModel>()
    val state: HomeContract.State by vm.state.collectAsState()

    HomeScreen(
        state = state,
        effect = vm.effect,
        onEventSend = { vm.setEvent(it) },
        onNavigationRequested = {
            when (it) {
                HomeContract.Effect.Navigation.NavigateStartRoverWalk -> navController.navigate(
                    WalkRoute
                )
            }
        }
    )
}