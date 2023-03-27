package com.example.fueladds.data

data class FuelMetaDataResponse(
    val fuelMetaData: FuelMetaData
)

data class FuelMetaData(
    val activeAccount: String?,
    val expire: String?
)
