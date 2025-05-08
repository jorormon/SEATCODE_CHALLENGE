package com.jordiortuno.roverapp.features.splash


import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordiortuno.roverapp.R
import com.jordiortuno.roverapp.components.RocketLottie
import com.jordiortuno.roverapp.ui.theme.RoverTheme
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navigateToHome:() ->Unit) {
    var currentBackground by remember { mutableIntStateOf(R.drawable.splash_earth_background) }
    var tint: Boolean by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(3000)
        currentBackground = R.drawable.splash_background
        tint = true
    }
    LaunchedEffect(Unit) {
        delay(6000)
        navigateToHome()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Crossfade(targetState = currentBackground, label = "") { background ->
            Image(
                painter = painterResource(background),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize(),
            )
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            RocketLottie(
                R.raw.rocket,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .weight(0.4f)
                    .size(200.dp)
            )

            Spacer(modifier = Modifier.weight(1f))
        }
        if (tint) {
            Spacer(modifier = Modifier
                .fillMaxSize()
                .background(Color.Red.copy(0.2f)))
        }
    }
}

@Preview(device = Devices.PIXEL_4_XL)
@Composable
fun SplashScreenPreview() {

    RoverTheme {
        SplashScreen(){}
    }
}