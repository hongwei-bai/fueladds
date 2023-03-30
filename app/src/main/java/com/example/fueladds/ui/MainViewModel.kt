package com.example.fueladds.ui

import android.util.Log
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
    private val fuelAppRepository: FuelAppRepository
) : ViewModel() {
//    private val _isG01ActiveState = MutableStateFlow(false)
//    val isG01ActiveState: StateFlow<Boolean> = _isG01ActiveState.asStateFlow()

    init {
        viewModelScope.launch {
            val fuelData = fuelAppRepository.fetchFuelMetaData()
            Log.d("bbbb", "fuelData: $fuelData")
            if (fuelData != null) {
//                _isG01ActiveState.value = fuelData.activeAccount == "G01"
            }
        }
    }
}