package com.example.fueladds.ui

import com.example.fueladds.data.FuelAppModel
import javax.inject.Inject

class HomeScreenUiStateMapper @Inject constructor() {
    fun mapToViewObject(model: FuelAppModel): HomeScreenUiState =
        HomeScreenUiState(
            cards = model.accounts.map { accountModel ->
                Card(
                    displayName = "G0${accountModel.id}",
                    isEnabled = accountModel.isLocked,
                    isHighlight = accountModel.isHighlight
                )
            }
        )
}