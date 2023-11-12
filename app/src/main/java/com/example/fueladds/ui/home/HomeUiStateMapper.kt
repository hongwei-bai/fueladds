package com.example.fueladds.ui.home

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.ui.text.toLowerCase
import com.example.fueladds.constant.DateConstants.DATE_FORMAT
import com.example.fueladds.constant.DateConstants.DATE_FORMAT_B1
import com.example.fueladds.constant.DateConstants.DATE_FORMAT_B2
import com.example.fueladds.data.local.OfflineFileRules
import com.example.fueladds.data.model.FuelAppModel
import com.example.fueladds.data.model.FuelAppModelBase
import com.example.fueladds.data.model.FuelAppModelError
import com.example.fueladds.ui.shared.UiState
import java.io.InputStream
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
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
                                isEnabled = accountModel.isLocked && !isOverdue(accountModel.expire),
                                isHighlight = accountModel.isHighlight,
                                isOverdue = isOverdue(accountModel.expire),
                                price = accountModel.lockedPrice,
                                expire = accountModel.expire,
                                expireIn = getTimeDiffDescription(accountModel.expire),
                                fuelType = mapFuelType(accountModel.fuelType)
                            )
                        }
                    )
                } else {
                    HomeUiState(UiState.Error)
                }
            }

            FuelAppModelError -> HomeUiState(UiState.Error)
        }

    private fun mapFuelType(fuelType: String?): FuelType =
        when (fuelType) {
            "U98", "u98", "98", "#98" -> FuelType.U98
            "U95", "u95", "95", "#95" -> FuelType.U95
            "U91", "u91", "91", "#91" -> FuelType.U91
            "E10", "e10" -> FuelType.E10
            "Diesel", "diesel" -> FuelType.Diesel
            else -> FuelType.Unknown
        }

    private fun getExpireCalendar(expireString: String?): Calendar? {
        if (expireString.isNullOrEmpty()) {
            return null
        }

        val trySet = listOf(
            DATE_FORMAT, DATE_FORMAT_B1, DATE_FORMAT_B2
        )
        trySet.forEach {
            val expireCalendar = tryParseCalendar(expireString, it)
            if (expireCalendar != null) {
                return expireCalendar
            }
        }
        return null
    }

    private fun tryParseCalendar(str: String, formatStr: String): Calendar? {
        val calendar = Calendar.getInstance()
        val format = SimpleDateFormat(formatStr, Locale.US)
        try {
            calendar.time = format.parse(str) as Date
        } catch (e: ParseException) {
            Log.w(HomeUiStateMapper::class.simpleName, "parse date failure: $e")
            return null
        }
        return calendar
    }

    private fun isOverdue(expireString: String?): Boolean {
        val expireCalendar = getExpireCalendar(expireString)
        return if (expireCalendar != null) {
            val now = Calendar.getInstance()
            expireCalendar.time < now.time
        } else {
            false
        }
    }

    private fun getTimeDiffDescription(expireString: String?): String {
        val expireCalendar = getExpireCalendar(expireString)
        if (expireCalendar != null) {
            val now = Calendar.getInstance()
            val diff = expireCalendar.timeInMillis - now.timeInMillis
            val days = diff / (1000 * 60 * 60 * 24)
            val hours = diff / (1000 * 60 * 60) % 24
            val minutes = diff / (1000 * 60) % 60

            val description = when {
                diff < 0 -> "Overdue"
                days > 0 -> "In $days day(s) and $hours hour(s)"
                hours > 0 -> "In $hours hour(s) and $minutes"
                minutes > 0 -> "In $minutes"
                else -> "Expiring Soon!"
            }
            return description
        } else {
            return ""
        }
    }
}