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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.fueladds.R
import java.util.*

@Preview(showBackground = true)
@Composable
fun CardScreen(@PreviewParameter(SampleAccountProvider::class) cardId: Int) {
    Row {
        Image(
            painter = painterResource(
                when (cardId) {
                    1 -> R.drawable.fuel_logo
                    2 -> R.drawable.fuel_logo
                    3 -> R.drawable.fuel_logo
                    else -> R.drawable.fuel_logo
                }
            ),
            contentDescription = "card",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.Top)
                .background(Color.White),
        )
    }
}

class SampleAccountProvider : PreviewParameterProvider<String> {
    override val values = sequenceOf("g01", "g02")
}