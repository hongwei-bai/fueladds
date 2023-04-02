package com.example.fueladds.ui.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fueladds.data.FuelAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val fuelAppRepository: FuelAppRepository,
    private val cardUiStateMapper: CardUiStateMapper
) : ViewModel() {
    val fuelCardModelFlow = fuelAppRepository.fuelCardModelFlow.map {
        cardUiStateMapper.mapToUiState(it)
    }

    fun loadCard(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            fuelAppRepository.fetchFuelCardImage(id)
        }
    }
}