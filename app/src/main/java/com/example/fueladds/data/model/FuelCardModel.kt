package com.example.fueladds.data.model

import android.graphics.Bitmap

sealed class FuelCardModelBase

data class FuelCardModel(
    val cardId: Int,
    val cardBitmap: Bitmap
) : FuelCardModelBase()

object FuelCardModelError : FuelCardModelBase()

