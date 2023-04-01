package com.example.fueladds

const val HTTP_READ_TIMEOUT = 15000L
const val HTTP_WRITE_TIMEOUT = 15000L
const val HTTP_CONNECT_TIMEOUT = 15000L
const val AUTHORIZATION_HEADER = "Authorization"
const val BEARER_TOKEN = "Bearer ${BuildConfig.staticApiToken}"

const val GITHUB_API_BASE_URL =
    "https://api.github.com/"

const val FUEL_APP_MAIN_ENDPOINT =
    "https://api.github.com/repos/hongwei-bai/static-api-experiment/contents/fuelapp/accounts/main.json"

const val FUEL_CARD_IMAGE_TEMPLATE_ENDPOINT =
    "https://api.github.com/repos/hongwei-bai/static-api-experiment/contents/fuelapp/accounts/fuel_g\${id}.png"