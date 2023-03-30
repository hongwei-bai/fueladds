package com.example.fueladds.ui

data class HomeScreenUiState(
    val cards: List<Card>
)

data class Card(
    val displayName: String,
    val isEnabled: Boolean,
    val isHighlight: Boolean
)