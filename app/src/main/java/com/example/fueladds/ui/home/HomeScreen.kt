package com.example.fueladds.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fueladds.R
import com.example.fueladds.ui.shared.ErrorScreen
import com.example.fueladds.ui.shared.LoadingScreen
import com.example.fueladds.ui.shared.NavigationPath
import com.example.fueladds.ui.shared.UiState

@Composable
fun HomeScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val uiState: HomeUiState by mainViewModel.fuelAppModelFlow.collectAsState(HomeUiState())

    LaunchedEffect(uiState) {
        mainViewModel.loadMainData()
    }

    Column(
        modifier = Modifier
            .padding(24.dp)
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        when (uiState.state) {
            UiState.Loading -> LoadingScreen(navController)
            UiState.Success -> {
                uiState.cards.forEach { card ->
                    FuelCard(card) {
                        navController.navigate("${NavigationPath.CardScreen}/${card.id}")
                    }
                    Spacer(modifier = Modifier.height(48.dp))
                }
                if (mainViewModel.isOfflineMode()) {
                    Spacer(modifier = Modifier.height(48.dp))
                    Text(
                        text = stringResource(id = R.string.offline_mode_message),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            else -> ErrorScreen(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FuelCard(
    @PreviewParameter(CardDataPreviewProvider::class) card: Card,
    clickAction: ((Int) -> Unit)? = null
) {
    val backgroundColor = MaterialTheme.colorScheme.primary
    Row {
        Button(
            enabled = card.isEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(backgroundColor)
                .requiredHeight(180.dp),
            shape = RoundedCornerShape(16.dp),
            onClick = {
                clickAction?.invoke(card.id)
            }) {
            Column {
                if (card.isEnabled || card.isOverdue) {
                    if (!card.isOverdue) {
                        Row {
                            getFuelTypeResId(card.fuelType)?.let {
                                Image(
                                    painter = painterResource(it),
                                    contentDescription = card.fuelType.name,
                                    contentScale = ContentScale.FillWidth,
                                    modifier = Modifier
                                        .align(Alignment.Top)
                                        .background(Color.White)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.requiredHeight(8.dp))
                    }
                    Row {
                        if (card.isHighlight) {
                            Icon(Icons.Default.Star, contentDescription = "Star")
                            Spacer(modifier = Modifier.requiredWidth(16.dp))
                        }
                        if (card.fuelType == FuelType.E10) {
                            Text(
                                text = stringResource(
                                    id = R.string.fuel_card_display_name_e10,
                                    card.id
                                ),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        } else {
                            Text(
                                text = stringResource(
                                    id = R.string.fuel_card_display_name,
                                    card.id
                                ),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                    if (card.price != null && card.expire != null && card.expireIn != null) {
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
                        Spacer(modifier = Modifier.requiredHeight(4.dp))
                        Row(
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = card.expireIn,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                } else {
                    Row {
                        Text(
                            text = stringResource(
                                id = R.string.fuel_card_display_name,
                                card.id
                            ),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Spacer(modifier = Modifier.requiredHeight(4.dp))
                    Row {
                        Text(
                            text = stringResource(R.string.card_disable_text, card.id),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

private fun getFuelTypeResId(fuelType: FuelType): Int? {
    return when (fuelType) {
        FuelType.U98 -> R.drawable.u98
        FuelType.U95 -> R.drawable.u95
        FuelType.U91 -> R.drawable.u91
        FuelType.E10 -> R.drawable.e10
        FuelType.Diesel -> R.drawable.diesel
        else -> null
    }
}

class CardDataPreviewProvider : PreviewParameterProvider<Card> {
    override val values: Sequence<Card>
        get() = sequenceOf(
            Card(
                id = 1,
                isEnabled = true,
                isHighlight = true,
                isOverdue = false,
                price = "\$189.5",
                expire = "2023-3-31 9:46 PM",
                expireIn = "2 Days",
                fuelType = FuelType.U98
            ),
            Card(
                id = 1,
                isEnabled = false,
                isHighlight = false,
                isOverdue = true,
                price = "\$189.7",
                expire = "2023-3-31 9:46 PM",
                expireIn = "Overdue",
                fuelType = FuelType.U98
            ),
            Card(
                id = 2,
                isEnabled = false,
                isHighlight = false,
                isOverdue = false,
                price = null,
                expire = null,
                expireIn = null,
                fuelType = FuelType.U91
            ),
        )
}