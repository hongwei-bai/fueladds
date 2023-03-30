package com.example.fueladds.data

import android.util.Base64
import com.google.gson.Gson
import javax.inject.Inject

class FuelAppRepository @Inject constructor(
    private val openApiService: OpenApiService
) {
    suspend fun getFuelAppModel(): FuelAppModel? {
        val response = openApiService.getFuelAppData()
        val body = response.body()
        return if (response.isSuccessful && body != null) {
            val encodedContent = body.content

            val decodedBytes = Base64.decode(encodedContent, Base64.DEFAULT)
            val decodedString = String(decodedBytes, Charsets.UTF_8)
            Gson().fromJson(decodedString, FuelAppModel::class.java)
        } else {
            null
        }
    }
}