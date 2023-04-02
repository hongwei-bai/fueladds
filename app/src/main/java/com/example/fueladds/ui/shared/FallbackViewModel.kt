package com.example.fueladds.ui.shared

import androidx.lifecycle.ViewModel
import com.example.fueladds.data.FuelAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FallbackViewModel @Inject constructor(
    private val fuelAppRepository: FuelAppRepository
) : ViewModel() {
    fun enableOfflineMode() {
        fuelAppRepository.enableOfficeMode()
    }
}