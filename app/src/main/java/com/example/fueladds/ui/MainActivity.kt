package com.example.fueladds.ui

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
import com.example.fueladds.ui.home.HomeScreen
import com.example.fueladds.ui.home.MainViewModel
import com.example.fueladds.ui.card.CardScreen
import com.example.fueladds.ui.shared.NavigationPath
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
    NavHost(navController, startDestination = NavigationPath.HomeScreen) {
        composable(NavigationPath.HomeScreen) {
            HomeScreen(navController, viewModel)
        }
        composable(NavigationPath.CardScreen) {
            CardScreen(1)
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