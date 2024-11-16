package com.example.connectify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.connectify.ui.navigation.SetupNavGraph
import com.example.connectify.ui.theme.ConnectifyTheme
//import com.example.connectify.ui.navigation.Screen
//import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
//import com.example.connectify.ui.screens.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConnectifyTheme {
                // Surface provides a background for the entire app
                Surface(color = MaterialTheme.colorScheme.background) {
                    // Initialize the navigation controller
                    val navController = rememberNavController()
                    // Set up the navigation graph
                    SetupNavGraph(navController = navController)
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ConnectifyApp() {
    val navController = rememberNavController()
    Surface(color = MaterialTheme.colorScheme.background) {
        SetupNavGraph(navController = navController)
    }}