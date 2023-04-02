package com.example.fueladds.ui.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.fueladds.R

@Composable
fun LoadingScreen(
    navController: NavController,
    fallbackViewModel: FallbackViewModel = hiltViewModel()
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.fuelapp_loading))
    val progress by animateLottieCompositionAsState(composition)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.requiredSize(128.dp, 128.dp)) {
            LottieAnimation(
                composition = composition,
                progress = { progress },
            )
        }
        Spacer(modifier = Modifier.requiredHeight(64.dp))
        Button(onClick = {
            fallbackViewModel.enableOfflineMode()
            navController.navigate(NavigationPath.HomeScreen)
        }) {
            Text(text = stringResource(id = R.string.offline_mode))
        }
    }
}