package com.example.fueladds.ui.card

import com.example.fueladds.data.model.FuelCardModel
import com.example.fueladds.data.model.FuelCardModelBase
import com.example.fueladds.data.model.FuelCardModelError
import com.example.fueladds.ui.shared.UiState
import javax.inject.Inject

class CardUiStateMapper @Inject constructor() {
    fun mapToUiState(model: FuelCardModelBase): CardUiState =
        when (model) {
            is FuelCardModel -> {
                CardUiState(
                    state = UiState.Success,
                    cardId = model.cardId,
                    cardImageBitmap = model.cardBitmap
                )
            }
            FuelCardModelError -> CardUiState(UiState.Error)
        }
}