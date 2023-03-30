package com.example.fueladds.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun HomeScreen(
    navController: NavController,
    mainViewModel: MainViewModel
) {
    val uiState by mainViewModel.fuelAppState.collectAsState()

    Column(
        modifier = Modifier.padding(24.dp)
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        uiState?.cards?.forEach { card ->
            FuelCard(card) {
                navController.navigate(card.displayName.lowercase())
            }
            Spacer(modifier = Modifier.height(26.dp))
        }
    }
}

@Preview
@Composable
fun FuelCard(
    @PreviewParameter(CardDataPreviewProvider::class) card: Card,
    clickAction: ((String) -> Unit)? = null
) {
    Row {
        Button(
            enabled = card.isEnabled,
            onClick = {
                clickAction?.invoke(card.displayName.lowercase())
            }) {
            Text(
                text = "Fuel App Account ${card.displayName}"
            )
        }
    }
}

class CardDataPreviewProvider : PreviewParameterProvider<Card> {
    override val values: Sequence<Card>
        get() = sequenceOf(
            Card(
                displayName = "Fuel App Account G01",
                isEnabled = true,
                isHighlight = false
            ),
            Card(
                displayName = "Fuel App Account G02",
                isEnabled = false,
                isHighlight = false
            ),
        )
}