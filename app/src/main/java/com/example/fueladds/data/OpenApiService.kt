package com.example.fueladds.data

import com.example.fueladds.FUEL_APP_METADATA_URL
import retrofit2.Response
import retrofit2.http.GET

interface OpenApiService {
    @GET(FUEL_APP_METADATA_URL)
    suspend fun getFuelMetaData(): Response<FuelMetaDataResponse>
}