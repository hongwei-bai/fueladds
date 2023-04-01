package com.example.fueladds.data

import com.example.fueladds.FUEL_APP_MAIN_ENDPOINT
import com.example.fueladds.FUEL_CARD_IMAGE_TEMPLATE_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface OpenApiService {
    @GET(FUEL_APP_MAIN_ENDPOINT)
    suspend fun getFuelAppData(): Response<GithubOpenApiResponse>

    @GET(FUEL_CARD_IMAGE_TEMPLATE_ENDPOINT)
    suspend fun getFuelCardImage(): Response<GithubOpenApiResponse>
}