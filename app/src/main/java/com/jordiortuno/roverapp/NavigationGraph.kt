package com.jordiortuno.roverapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jordiortuno.roverapp.features.home.HomeDestination
import com.jordiortuno.roverapp.features.home.HomeRoute
import com.jordiortuno.roverapp.features.splash.SplashRoute
import com.jordiortuno.roverapp.features.splash.SplashScreen
import com.jordiortuno.roverapp.features.walk.WalkDestination
import com.jordiortuno.roverapp.features.walk.WalkRoute

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.Transparent
    ) { _ ->
        NavHost(
            navController = navController,
            startDestination = SplashRoute,
            modifier = Modifier
        ) {
            composable<SplashRoute> {
                SplashScreen {
                    navController.navigate(HomeRoute){
                        popUpTo(SplashRoute) {
                            inclusive = true
                        }
                    }
                }
            }
            composable<HomeRoute> {
                HomeDestination(navController)
            }
            composable<WalkRoute> {
                WalkDestination(navController)
            }

        }
    }
}