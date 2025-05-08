package com.jordiortuno.roverapp.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordiortuno.rover.presentation.viewmodel.home.HomeContract
import com.jordiortuno.roverapp.R
import com.jordiortuno.roverapp.components.GameButton
import com.jordiortuno.roverapp.ui.theme.RoverTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    state: HomeContract.State,
    effect: Flow<HomeContract.Effect>,
    onEventSend: (HomeContract.Event) -> Unit,
    onNavigationRequested: (HomeContract.Effect.Navigation) -> Unit,
) {
    LaunchedEffect(key1 = true) {
        effect.collectLatest { effect: HomeContract.Effect ->
            when (effect) {
                is HomeContract.Effect.Navigation -> {
                    onNavigationRequested(effect)
                }
            }
        }
    }

    HomeScreenContent(onEventSend)

}




@Composable
fun HomeScreenContent(onEventSend: (HomeContract.Event) -> Unit) {
    Image(
        painter = painterResource(R.drawable.splash_background),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize(),
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.welcome_message),
            style = MaterialTheme.typography.displayMedium,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(24.dp))
        GameButton(stringResource(R.string.start), {onEventSend(HomeContract.Event.OnClickStart)}, modifier = Modifier.padding(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenContentPreview() {
    RoverTheme {
        HomeScreenContent(onEventSend = {})
    }
}