package com.example.fueladds.ui.home

import com.example.fueladds.data.model.FuelAppModelError
import com.example.fueladds.data.model.FuelAppModel
import com.example.fueladds.data.model.FuelAppModelBase
import com.example.fueladds.ui.shared.UiState
import javax.inject.Inject

class HomeUiStateMapper @Inject constructor() {
    fun mapToUiState(model: FuelAppModelBase): HomeUiState =
        when (model) {
            is FuelAppModel -> {
                if (model.accounts.isNotEmpty()) {
                    HomeUiState(
                        state = UiState.Success,
                        cards = model.accounts.map { accountModel ->
                            Card(
                                id = accountModel.id,
                                isEnabled = accountModel.isLocked,
                                isHighlight = accountModel.isHighlight,
                                price = accountModel.lockedPrice,
                                expire = accountModel.expire
                            )
                        }
                    )
                } else {
                    HomeUiState(UiState.Error)
                }
            }
            FuelAppModelError -> HomeUiState(UiState.Error)
        }
}