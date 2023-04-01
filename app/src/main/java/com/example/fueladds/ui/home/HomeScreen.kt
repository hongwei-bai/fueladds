package com.example.fueladds.ui.home

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
import com.example.fueladds.ui.shared.ErrorScreen
import com.example.fueladds.ui.shared.LoadingScreen
import com.example.fueladds.ui.shared.UiState


@Composable
fun HomeScreen(
    navController: NavController,
    mainViewModel: MainViewModel
) {
    val uiState: HomeUiState by mainViewModel.fuelAppModelFlow.collectAsState(HomeUiState())

    Column(
        modifier = Modifier.padding(24.dp)
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        when (uiState.state) {
            UiState.Loading -> LoadingScreen()
            UiState.Success -> {
                uiState.cards.forEach { card ->
                    FuelCard(card) {
                        navController.navigate(card.name.lowercase())
                    }
                    Spacer(modifier = Modifier.height(26.dp))
                }
            }
            else -> ErrorScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FuelCard(
    @PreviewParameter(CardDataPreviewProvider::class) card: Card,
    clickAction: ((String) -> Unit)? = null
) {
    Row {
        Button(
            enabled = card.isEnabled,
            onClick = {
                clickAction?.invoke(card.name.lowercase())
            }) {
            Text(
                text = "Fuel App Account ${card.name}"
            )
        }
    }
}

class CardDataPreviewProvider : PreviewParameterProvider<Card> {
    override val values: Sequence<Card>
        get() = sequenceOf(
            Card(
                name = "G01",
                isEnabled = true,
                isHighlight = false
            ),
            Card(
                name = "G02",
                isEnabled = false,
                isHighlight = false
            ),
        )
}