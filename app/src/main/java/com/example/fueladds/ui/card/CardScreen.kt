package com.example.fueladds.ui.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.fueladds.R
import java.util.*

@Composable
fun CardScreen(account: String) {
    Row {
        Image(
            painter = painterResource(
                when (account) {
                    "g01" -> R.drawable.fuel_g01
                    "g02" -> R.drawable.fuel_g02
                    "g03" -> R.drawable.fuel_g03
                    else -> R.drawable.fuel_g01
                }
            ),
            contentDescription = account.uppercase(Locale.US),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.Top)
                .background(Color.White),
        )
    }
}