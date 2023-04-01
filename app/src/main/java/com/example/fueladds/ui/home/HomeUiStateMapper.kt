package com.example.fueladds.ui.home

import com.example.fueladds.data.FuelAppModel
import com.example.fueladds.ui.shared.UiState
import javax.inject.Inject

class HomeUiStateMapper @Inject constructor() {
    fun mapToViewObject(model: FuelAppModel): HomeUiState =
        if (model.accounts.isNotEmpty()) {
            HomeUiState(
                state = UiState.Success,
                cards = model.accounts.map { accountModel ->
                    Card(
                        name = "G0${accountModel.id}",
                        isEnabled = accountModel.isLocked,
                        isHighlight = accountModel.isHighlight
                    )
                },
                cardImage = hashMapOf()
            )
        } else {
            HomeUiState(UiState.Error)
        }
}