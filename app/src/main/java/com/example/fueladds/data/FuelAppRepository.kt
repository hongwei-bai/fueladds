package com.example.fueladds.data

import android.util.Base64
import android.util.Log
import javax.inject.Inject

class FuelAppRepository @Inject constructor(
    private val openApiService: OpenApiService
) {
    suspend fun fetchFuelMetaData(): FuelAppModel? {
        val response = openApiService.getFuelAppData()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            val encoding = body.encoding
            val encodedContent = body.content
            Log.d("bbbb", "response.encoding: $encoding")
            Log.d("bbbb", "response.content: $encodedContent")

            val decodedBytes = Base64.decode(encodedContent, Base64.DEFAULT)
            val decodedString = String(decodedBytes, Charsets.UTF_8)
            Log.d("bbbb", "decodedString: $decodedString")

            return null

        } else {
            return null
        }
    }
}