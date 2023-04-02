package com.example.fueladds.data

data class FuelAppModel(
    val accounts: List<Account>
)

data class Account(
    val id: Int,
    val isLocked: Boolean,
    val lockedPrice: String,
    val isHighlight: Boolean,
    val expire: String
)