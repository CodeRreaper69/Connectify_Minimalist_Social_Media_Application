package com.example.connectify.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.connectify.ui.navigation.Screen
import androidx.navigation.compose.rememberNavController
import com.example.connectify.ui.theme.ConnectifyTheme
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SettingsScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.surface
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                // Title
                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Account Settings Section
                SettingsSection(title = "Account Settings")
                SettingsOption(text = "Change Password") { /* Navigate to Change Password */ }
                SettingsOption(text = "Privacy Settings") { /* Navigate to Privacy Settings */ }
                SettingsOption(text = "Security Settings") { /* Navigate to Security Settings */ }

                Spacer(modifier = Modifier.height(24.dp))

                // Notifications Section
                SettingsSection(title = "Notifications")
                SettingsOption(text = "Push Notifications") { /* Navigate to Push Notifications */ }
                SettingsOption(text = "Email Notifications") { /* Navigate to Email Notifications */ }
                SettingsOption(text = "SMS Notifications") { /* Navigate to SMS Notifications */ }

                Spacer(modifier = Modifier.height(24.dp))

                // App Settings Section
                SettingsSection(title = "App Settings")
                SettingsOption(text = "Language") { /* Navigate to Language Settings */ }
                SettingsOption(text = "Theme") { /* Navigate to Theme Selection */ }

                Spacer(modifier = Modifier.height(24.dp))

                // Back to Home Button
                Button(
                    onClick = { navController.navigate(Screen.Home.route) },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(
                        text = "Back to Home",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsSection(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun SettingsOption(text: String, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true, name = "Dark Mode")
@Composable
fun PreviewSettingsScreen() {
    ConnectifyTheme {
        SettingsScreen(navController = rememberNavController())
    }
}
