package com.example.fueladds.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Row {
            Button(
                onClick = {
                    navController.navigate("g01")
                }) {
                Text(
                    text = "Fuel App Account G01"
                )
            }
        }
        Row {
            Button(onClick = {
                navController.navigate("g02")
            }) {
                Text(text = "Fuel App Account G02")
            }
        }
        Row {
            Button(onClick = {
                navController.navigate("g03")
            }) {
                Text(text = "Fuel App Account G03")
            }
        }
    }
}