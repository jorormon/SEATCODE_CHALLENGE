package com.jordiortuno.rover.framework.source

import android.content.Context
import com.jordiortuno.rover.data.model.RoverInstructionsData
import com.jordiortuno.rover.data.sources.RoverRemoteDataSource
import com.jordiortuno.rover.framework.source.model.RoverResponse
import kotlinx.serialization.json.Json


class RoverJsonDataSource(private val context: Context) : RoverRemoteDataSource {
    private val json = Json { ignoreUnknownKeys = true }
    override suspend fun getRoverInstructions(): RoverInstructionsData {
        val fileName = "response.json"
        val assetManager = context.assets
        val jsonString = assetManager.open(fileName).bufferedReader().use { it.readText() }
        val response : RoverResponse =  parseJson(jsonString)
        return response.toData()
    }

    private inline fun <reified T> parseJson(jsonString: String): T {
        return json.decodeFromString(jsonString)
    }
}