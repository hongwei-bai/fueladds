package com.example.fueladds.ui.home

import android.graphics.Bitmap
import com.example.fueladds.ui.shared.UiState


data class HomeUiState(
    val state: UiState = UiState.Loading,
    val cards: List<Card> = emptyList()
)

data class Card(
    val id: Int,
    val isEnabled: Boolean,
    val isOverdue: Boolean,
    val isHighlight: Boolean,
    val price: String?,
    val expire: String?,
    val expireIn: String?,
    val fuelType: FuelType
)

enum class FuelType {
    U98, U95, U91, E10, Diesel, Unknown
}