package com.example.fueladds.ui.card

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fueladds.ui.shared.LoadingScreen
import kotlinx.coroutines.flow.filter

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun CardScreen(
    navController: NavController,
    cardId: Int,
    cardViewModel: CardViewModel = hiltViewModel(),
) {
    val cardUiState by cardViewModel.fuelCardModelFlow
        .filter { it.cardId == cardId }
        .collectAsState(CardUiState())

    LaunchedEffect(true) {
        cardViewModel.loadCard(cardId)
    }

    cardUiState.cardImageBitmap?.let {
        Row {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "card",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .align(Alignment.Top)
                    .background(Color.White)
            )
        }
    } ?: LoadingScreen(navController)
}