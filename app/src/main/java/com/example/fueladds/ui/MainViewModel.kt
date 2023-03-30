package com.example.fueladds.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fueladds.data.FuelAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fuelAppRepository: FuelAppRepository,
    private val uiStateMapper: HomeScreenUiStateMapper
) : ViewModel() {
    private val _fuelAppState: MutableStateFlow<HomeScreenUiState?> =
        MutableStateFlow(null)
    val fuelAppState: StateFlow<HomeScreenUiState?> = _fuelAppState.asStateFlow()

    init {
        viewModelScope.launch {
            val fuelAppModel = fuelAppRepository.getFuelAppModel()
            if (fuelAppModel != null) {
                val viewObject = uiStateMapper.mapToViewObject(fuelAppModel)
                _fuelAppState.value = viewObject
            }
        }
    }
}