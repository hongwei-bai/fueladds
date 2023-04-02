package com.example.fueladds.ui.home

import android.graphics.Bitmap
import com.example.fueladds.ui.shared.UiState


data class HomeUiState(
    val state: UiState = UiState.Loading,
    val cards: List<Card> = emptyList(),
    val cardImage: HashMap<String, Bitmap> = hashMapOf()
)

data class Card(
    val id: Int,
    val isEnabled: Boolean,
    val isHighlight: Boolean,
    val price: String?,
    val expire: String?
)
