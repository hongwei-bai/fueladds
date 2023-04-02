package com.example.fueladds.data.remote

import com.example.fueladds.constant.FUEL_APP_MAIN_ENDPOINT
import com.example.fueladds.constant.FUEL_CARD_IMAGE_TEMPLATE_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface OpenApiService {
    @GET(FUEL_APP_MAIN_ENDPOINT)
    suspend fun getFuelAppData(): Response<GithubOpenApiResponse>

    @GET(FUEL_CARD_IMAGE_TEMPLATE_ENDPOINT)
    suspend fun getFuelCardImage(@Path("id") id: String): Response<GithubOpenApiResponse>
}