package com.example.connectify.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.connectify.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun WelcomeScreen(navController: NavController) {
    var isLoading by remember { mutableStateOf(true) }
    var connectionStatus by remember { mutableStateOf("Connecting...") }
    var connectionSuccess by remember { mutableStateOf(false) }

    // Simulate connection as a Composable function
    @Composable
    fun SimulateConnection() {
        LaunchedEffect(Unit) {
            delay(2000) // Simulate initial loading time
            connectionStatus = if (Random.nextDouble() <= 0.7) {
                connectionSuccess = true
                "Connected Successfully!"
            } else {
                connectionSuccess = false
                "Connection Failed!"
            }
            delay(1000)
            isLoading = false
        }
    }

    // Call the connection simulation
    if (isLoading) {
        SimulateConnection()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Gradient Background
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
            )

            // Main Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Logo
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .shadow(8.dp, CircleShape)
                        .background(MaterialTheme.colorScheme.surface, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.connectify_cropped),
                        contentDescription = "App Logo",
                        modifier = Modifier.size(100.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                // Welcome Text
                Text(
                    text = "Welcome to Connectify",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    ),
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Loading or Connection Status
                if (isLoading) {
                    // Rotating Loading Indicator
                    val rotation by rememberInfiniteTransition().animateFloat(
                        initialValue = 0f,
                        targetValue = 360f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(1000, easing = LinearEasing)
                        )
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_loading),
                        contentDescription = "Loading",
                        modifier = Modifier
                            .size(50.dp)
                            .rotate(rotation),
                        tint = MaterialTheme.colorScheme.primary
                    )
                } else {
                    // Connection Status Text
                    Text(
                        text = "Database $connectionStatus",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.ExtraLight,
                            fontSize = 16.sp
                        ),
                        color = if (connectionSuccess)
                            MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.error
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Buttons (show only after loading)
                if (!isLoading) {
                    // Retry Connection Button (if connection failed)
                    if (!connectionSuccess) {
                        Button(
                            onClick = {
                                isLoading = true
                                connectionStatus = "Connecting..."
                            },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error
                            )
                        ) {
                            Text(
                                text = "Retry Connection",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.onError
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Sign In Button
                    Button(
                        onClick = {
                            if (connectionSuccess) {
                                navController.navigate("sign_in_screen")
                            }
                        },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (connectionSuccess)
                                MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.surface,
                            disabledContainerColor = MaterialTheme.colorScheme.surface
                        ),
                        enabled = connectionSuccess
                    ) {
                        Text(
                            text = "Sign In",
                            style = MaterialTheme.typography.labelLarge,
                            color = if (connectionSuccess)
                                MaterialTheme.colorScheme.onPrimary
                            else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Sign Up Button
                    Button(
                        onClick = {
                            if (connectionSuccess) {
                                navController.navigate("sign_up_screen")
                            }
                        },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (connectionSuccess)
                                MaterialTheme.colorScheme.secondary
                            else MaterialTheme.colorScheme.surface,
                            disabledContainerColor = MaterialTheme.colorScheme.surface
                        ),
                        enabled = connectionSuccess
                    ) {
                        Text(
                            text = "Sign Up",
                            style = MaterialTheme.typography.labelLarge,
                            color = if (connectionSuccess)
                                MaterialTheme.colorScheme.onSecondary
                            else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Dark Mode")
@Composable
fun PreviewWelcomeScreen() {
    com.example.connectify.ui.theme.ConnectifyTheme {
        WelcomeScreen(navController = rememberNavController())
    }
}