package com.example.fueladds.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fueladds.R
import com.example.fueladds.ui.shared.ErrorScreen
import com.example.fueladds.ui.shared.LoadingScreen
import com.example.fueladds.ui.shared.NavigationPath
import com.example.fueladds.ui.shared.UiState

@Composable
fun HomeScreen(
    navController: NavController,
    mainViewModel: MainViewModel
) {
    val uiState: HomeUiState by mainViewModel.fuelAppModelFlow.collectAsState(HomeUiState())

    Column(
        modifier = Modifier
            .padding(24.dp)
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        when (uiState.state) {
            UiState.Loading -> LoadingScreen()
            UiState.Success -> {
                uiState.cards.forEach { card ->
                    FuelCard(card) {
                        navController.navigate("${NavigationPath.CardScreen}/${card.id}")
                    }
                    Spacer(modifier = Modifier.height(48.dp))
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
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(96.dp),
            shape = RoundedCornerShape(16.dp),
            onClick = {
                clickAction?.invoke(card.id)
            }) {
            Column {
                Row {
                    if (card.isHighlight) {
                        Icon(Icons.Default.Star, contentDescription = "Star")
                        Spacer(modifier = Modifier.requiredWidth(16.dp))
                    }
                    Text(
                        text = stringResource(id = R.string.fuel_card_display_name, card.id),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                if (card.isEnabled && card.price != null && card.expire != null) {
                    Spacer(modifier = Modifier.requiredHeight(4.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = card.price,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.requiredWidth(16.dp))
                        Text(
                            text = card.expire,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

class CardDataPreviewProvider : PreviewParameterProvider<Card> {
    override val values: Sequence<Card>
        get() = sequenceOf(
            Card(
                id = 1,
                isEnabled = true,
                isHighlight = false,
                price = "\$189.5",
                expire = "2023-3-31 9:46 PM"
            ),
            Card(
                id = 2,
                isEnabled = false,
                isHighlight = false,
                price = null,
                expire = null
            ),
        )
}