package com.example.praktikum2.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.praktikum2.navigation.Screen

@Composable
fun ResultScreen(name: String, navController: NavController) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Halo, $name!",
                style = MaterialTheme.typography.headlineMedium)

            Button(onClick = {
                navController.navigate(route = Screen.Home.route)
            }) {
                Text("Kembali")
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun ResultPreview() {
    ResultScreen(
        name = "",
        navController = rememberNavController()
    )
}