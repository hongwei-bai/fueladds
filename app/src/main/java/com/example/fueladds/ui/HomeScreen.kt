package com.example.fueladds.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(
//    navController: NavHostController,
    mainViewModel: MainViewModel = viewModel()
) {
    val isG01ActiveState by mainViewModel.isG01ActiveState.collectAsState()
    val isG02ActiveState by mainViewModel.isG02ActiveState.collectAsState()
    val isG03ActiveState by mainViewModel.isG03ActiveState.collectAsState()

    Column(
        modifier = Modifier.padding(24.dp)
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Row {
            Button(
                onClick = {
//                    navController.navigate("g01")
                }) {
                if (isG01ActiveState) {
                    Text(
                        text = "<A>Fuel App Account G01"
                    )
                } else {
                    Text(
                        text = "Fuel App Account G01"
                    )
                }
            }
        }
        Row {
            Button(onClick = {
//                navController.navigate("g02")
            }) {
                if (isG02ActiveState) {
                    Text(
                        text = "<A>Fuel App Account G02"
                    )
                } else {
                    Text(
                        text = "Fuel App Account G02"
                    )
                }
            }
        }
        Row {
            Button(onClick = {
//                navController.navigate("g03")
            }) {
                if (isG03ActiveState) {
                    Text(
                        text = "<A>Fuel App Account G03"
                    )
                } else {
                    Text(
                        text = "Fuel App Account G03"
                    )
                }
            }
        }
    }
}

