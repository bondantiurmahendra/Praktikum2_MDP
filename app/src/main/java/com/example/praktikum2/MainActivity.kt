package com.example.praktikum2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.praktikum2.navigation.SetupNavGraph
import com.example.praktikum2.ui.theme.Praktikum2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Praktikum2Theme {
                val navController = rememberNavController()
                SetupNavGraph(navController)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenView() {
    Praktikum2Theme {
        val navController = rememberNavController()
        SetupNavGraph(navController)
    }
}