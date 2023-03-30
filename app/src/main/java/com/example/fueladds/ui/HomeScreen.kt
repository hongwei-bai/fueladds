package com.example.fueladds.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun HomeScreen(
    navController: NavController,
    mainViewModel: MainViewModel
) {
//    val isG01ActiveState by mainViewModel.isG01ActiveState.collectAsState()

    SideEffect { println("HomeScreen+") }
    Column(
        modifier = Modifier.padding(24.dp)
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Row {
            Button(
                onClick = {
                    navController?.navigate("g01")
                }) {
                if (true) {
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
                navController?.navigate("g02")
            }) {
                if (true) {
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
                navController?.navigate("g03")
            }) {
                if (true) {
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