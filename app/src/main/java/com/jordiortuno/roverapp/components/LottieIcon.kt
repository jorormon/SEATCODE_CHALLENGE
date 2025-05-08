package com.jordiortuno.roverapp.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun RocketLottie(
    icon: Int,
    modifier: Modifier = Modifier,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(icon))
    val iterations = 2

    val progress by animateLottieCompositionAsState(
        iterations = iterations,
        composition = composition,
        speed = 1f,
        restartOnPlay = true,
        reverseOnRepeat = true
    )

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        progress = {
            progress
        }
    )
}