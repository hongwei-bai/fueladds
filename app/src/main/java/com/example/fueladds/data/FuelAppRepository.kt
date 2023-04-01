package com.example.fueladds.data

import android.util.Base64
import com.google.gson.Gson
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class FuelAppRepository @Inject constructor(
    private val openApiService: OpenApiService
) {
    private val fuelAppModelChannel = Channel<FuelAppModel>()
    val fuelAppModelFlow = fuelAppModelChannel.receiveAsFlow()

    suspend fun fetchFuelAppData() {
        val response = openApiService.getFuelAppData()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            val encodedContent = body.content

            val decodedBytes = Base64.decode(encodedContent, Base64.DEFAULT)
            val decodedString = String(decodedBytes, Charsets.UTF_8)
            fuelAppModelChannel.send(Gson().fromJson(decodedString, FuelAppModel::class.java))
        }
    }
}