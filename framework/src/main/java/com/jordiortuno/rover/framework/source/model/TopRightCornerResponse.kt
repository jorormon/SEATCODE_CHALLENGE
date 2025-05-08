package com.jordiortuno.rover.framework.source.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopRightCornerResponse(
    @SerialName("x")
    val x: Int,
    @SerialName("y")
    val y: Int
)