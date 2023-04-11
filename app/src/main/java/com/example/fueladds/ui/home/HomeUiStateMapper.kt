package com.example.fueladds.ui.home

import com.example.fueladds.constant.DateConstants.DATE_FORMAT
import com.example.fueladds.data.model.FuelAppModel
import com.example.fueladds.data.model.FuelAppModelBase
import com.example.fueladds.data.model.FuelAppModelError
import com.example.fueladds.ui.shared.UiState
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
                                expireIn = getTimeDiffDescription(accountModel.expire)
                            )
                        }
                    )
                } else {
                    HomeUiState(UiState.Error)
                }
            }
            FuelAppModelError -> HomeUiState(UiState.Error)
        }

    private fun getExpireCalendar(expireString: String?): Calendar? {
        if (expireString == null || expireString.isEmpty()) {
            return null
        }

        val expireCalendar = Calendar.getInstance()
        val format = SimpleDateFormat(DATE_FORMAT, Locale.US)
        expireCalendar.time = format.parse(expireString) as Date
        return expireCalendar
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