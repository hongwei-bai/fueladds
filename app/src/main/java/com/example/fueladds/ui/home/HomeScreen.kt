package com.example.fueladds.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fueladds.ui.shared.ErrorScreen
import com.example.fueladds.ui.shared.LoadingScreen
import com.example.fueladds.ui.shared.NavigationPath
import com.example.fueladds.ui.shared.UiState
import com.example.fueladds.R

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
                        navController.navigate("${NavigationPath.CardScreen}/${card.id}")
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
    clickAction: ((Int) -> Unit)? = null
) {
    Row {
        Button(
            enabled = card.isEnabled,
            onClick = {
                clickAction?.invoke(card.id)
            }) {
            Text(
                text = stringResource(id = R.string.fuel_card_display_name, card.id)
            )
        }
    }
}

class CardDataPreviewProvider : PreviewParameterProvider<Card> {
    override val values: Sequence<Card>
        get() = sequenceOf(
            Card(
                id = 1,
                isEnabled = true,
                isHighlight = false
            ),
            Card(
                id = 2,
                isEnabled = false,
                isHighlight = false
            ),
        )
}