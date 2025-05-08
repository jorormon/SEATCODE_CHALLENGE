package com.jordiortuno.roverapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFF5D0B0B),
    textColor: Color = Color.White,
    borderStroke: Dp = 2.dp,
) {
    Box(
        modifier = modifier
            .size(width = 150.dp, height = 50.dp)
            .graphicsLayer {
                shadowElevation = 10f
                shape = RoundedCornerShape(5.dp)
                clip = true
            }
            .background(backgroundColor, RoundedCornerShape(15.dp))
            .border(
                borderStroke,
                SolidColor(Color.Black),
                shape = MaterialTheme.shapes.medium
            )
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}