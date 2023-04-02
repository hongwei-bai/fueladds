package com.example.fueladds.ui.card

import android.graphics.Bitmap
import com.example.fueladds.ui.shared.UiState


data class CardUiState(
    val state: UiState = UiState.Loading,
    val cardId: Int = -1,
    val cardImageBitmap: Bitmap? = null
)
