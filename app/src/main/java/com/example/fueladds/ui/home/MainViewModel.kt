package com.example.fueladds.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fueladds.data.FuelAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fuelAppRepository: FuelAppRepository,
    private val uiStateMapper: HomeUiStateMapper
) : ViewModel() {
    val fuelAppModelFlow = fuelAppRepository.fuelAppModelFlow.map {
        uiStateMapper.mapToViewObject(it)
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            fuelAppRepository.fetchFuelAppData()
        }
    }
}