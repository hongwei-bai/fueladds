package com.example.fueladds.ui.home

import com.example.fueladds.ui.shared.UiState


data class HomeUiState(
    val state: UiState = UiState.Loading,
    val cards: List<Card> = emptyList(),
    val cardImage: HashMap<String, String> = hashMapOf()
)

data class Card(
    val name: String, val isEnabled: Boolean, val isHighlight: Boolean
)
