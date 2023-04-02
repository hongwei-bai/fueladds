package com.example.fueladds.data

import com.example.fueladds.constant.FUEL_CARD_ID_STRING_FORMAT_TEMPLATE
import com.example.fueladds.utility.Base64Utils.decodeBase64ToBitmap
import com.example.fueladds.utility.Base64Utils.decodeBase64ToString
import com.google.gson.Gson
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class FuelAppRepository @Inject constructor(
    private val openApiService: OpenApiService
) {
    private val fuelAppModelChannel = Channel<FuelAppModel>()
    val fuelAppModelFlow = fuelAppModelChannel.receiveAsFlow()

    private val fuelCardModelChannel = Channel<FuelCardModel>()
    val fuelCardModelFlow = fuelCardModelChannel.receiveAsFlow()

    suspend fun fetchFuelAppData() {
        val response = openApiService.getFuelAppData()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            val encodedContent = body.content
            val fuelAppModel =
                Gson().fromJson(decodeBase64ToString(encodedContent), FuelAppModel::class.java)
            fuelAppModelChannel.send(fuelAppModel)
        }
    }

    suspend fun fetchFuelCardImage(cardId: Int) {
        val stringId = String.format(FUEL_CARD_ID_STRING_FORMAT_TEMPLATE, cardId)
        val response = openApiService.getFuelCardImage(stringId)
        val body = response.body()
        if (response.isSuccessful && body != null) {
            val encodedContent = body.content
            val imageBitmap = decodeBase64ToBitmap(encodedContent)
            if (imageBitmap != null) {
                val fuelCardModel = FuelCardModel(
                    cardId = cardId,
                    cardBitmap = imageBitmap
                )
                fuelCardModelChannel.send(fuelCardModel)
            }
        }
    }
}