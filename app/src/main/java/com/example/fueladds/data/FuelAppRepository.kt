package com.example.fueladds.data

import android.util.Log
import javax.inject.Inject

class FuelAppRepository @Inject constructor(
    private val openApiService: OpenApiService
) {
    suspend fun fetchFuelMetaData(): FuelMetaData? {
        val response = openApiService.getFuelMetaData()
        Log.d("bbbb", "response: ${response.body()?.fuelMetaData}")
        return response.body()?.fuelMetaData
    }
}