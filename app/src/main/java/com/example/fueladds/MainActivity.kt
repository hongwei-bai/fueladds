package com.example.fueladds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fueladds.ui.HomeScreen
import com.example.fueladds.ui.MainViewModel
import com.example.fueladds.ui.card.CardScreen
import com.example.fueladds.ui.theme.FuelAddsTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FuelAddsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SystemUiController()
                    NavComposeApp()
                }
            }
        }
    }
}

@Composable
fun NavComposeApp() {
    val navController = rememberNavController()
    val viewModel: MainViewModel = viewModel()
    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController, viewModel)
        }
        composable("g01") {
            CardScreen("g01")
        }
        composable("g02") {
            CardScreen("g02")
        }
        composable("g03") {
            CardScreen("g03")
        }
    }
}

@Composable
fun SystemUiController() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = false //MaterialTheme.colors.isLight

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.White,
            darkIcons = useDarkIcons
        )
    }
}