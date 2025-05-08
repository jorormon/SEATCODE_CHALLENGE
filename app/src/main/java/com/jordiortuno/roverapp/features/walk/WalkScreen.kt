package com.jordiortuno.roverapp.features.walk

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.jordiortuno.rover.presentation.viewmodel.walk.Direction
import com.jordiortuno.rover.presentation.viewmodel.walk.WalkContract
import com.jordiortuno.roverapp.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
        state.uiModel != null -> state.uiModel?.let { WalkScreenContent(it, onEventSend) }
    }

}

@Composable
fun WalkScreenContent(
    uiModel: WalkContract.State.UiModel,
    onEventSend: (WalkContract.Event) -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(uiModel.grid.columns),
            modifier = Modifier.padding(16.dp)
        ) {
            items(uiModel.grid.numberOfCells) { index ->
                val x = index % uiModel.grid.columns
                val y = uiModel.grid.columns - 1 - (index / uiModel.grid.columns)


                Cell(x, y)

                if (uiModel.roverPosition.x == x && uiModel.roverPosition.y == y) {
                    Image(
                        painter = painterResource(R.drawable.rover),
                        contentDescription = null,
                        modifier = Modifier
                            .size(56.dp)
                            .rotate(
                                when (uiModel.roverPosition.direction) {
                                    Direction.NORTH -> 0f
                                    Direction.WEST -> 270f
                                    Direction.SOUTH -> 180f
                                    Direction.EST -> 90f
                                }
                            )
                    )
                }
            }
        }


        Button({ onEventSend(WalkContract.Event.PlayMovement) }) {
            Text("Play")
        }
    }
}

@Composable
fun Cell(x: Int, y: Int) {
    Surface(
        modifier = Modifier
            .size(60.dp),
        color = if ((x + y) % 2 == 0) Color.Gray.copy(alpha = 0.3f) else Color.LightGray.copy(alpha = 0.3f),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "($x, $y)",
                style = TextStyle(
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
        }
    }
}