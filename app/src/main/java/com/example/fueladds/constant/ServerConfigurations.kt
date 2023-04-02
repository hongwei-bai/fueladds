package com.example.fueladds.constant

import com.example.fueladds.BuildConfig

const val HTTP_READ_TIMEOUT = 15000L
const val HTTP_WRITE_TIMEOUT = 15000L
const val HTTP_CONNECT_TIMEOUT = 15000L
const val AUTHORIZATION_HEADER = "Authorization"
const val BEARER_TOKEN = "Bearer ${BuildConfig.staticApiToken}"
