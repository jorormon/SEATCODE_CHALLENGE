package com.jordiortuno.roverapp.features.walk

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jordiortuno.rover.presentation.viewmodel.walk.WalkContract
import com.jordiortuno.roverapp.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun WalkScreen(
    state: WalkContract.State,
    effect: Flow<WalkContract.Effect>,
    onEventSend: (WalkContract.Event) -> Unit,
    onNavigationRequested: (WalkContract.Effect.Navigation) -> Unit,
) {
    LaunchedEffect(key1 = true) {
        effect.collectLatest { effect: WalkContract.Effect ->
            when (effect) {
                is WalkContract.Effect.Navigation -> {
                    onNavigationRequested(effect)
                }

                else -> {}
            }
        }
    }
    Image(
        painter = painterResource(R.drawable.game_board),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize(),
    )
    when {
        state.uiModel != null -> state.uiModel?.let { WalkScreenContent(it) }
    }

}

@Composable
fun WalkScreenContent(uiModel: WalkContract.State.UiModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(uiModel.grid.columns),
            modifier = Modifier
                .padding(16.dp)
        ) {
            items(uiModel.grid.numberOfCells) { index ->
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                        )
                        .padding(4.dp)
                )
            }
        }
    }
}