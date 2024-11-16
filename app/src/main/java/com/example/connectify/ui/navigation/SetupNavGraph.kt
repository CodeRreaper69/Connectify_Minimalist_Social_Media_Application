package com.example.connectify.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.connectify.ui.screens.*

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        // Welcome Screen
        composable(Screen.Welcome.route) { WelcomeScreen(navController) }
        // Sign In Screen
        composable(Screen.SignIn.route) { SignInScreen(navController) }
        // Sign Up Screen
        composable(Screen.SignUp.route) { SignUpScreen(navController) }
        // Home Screen
        composable(Screen.Home.route) { HomeScreen(navController) }
        // Profile Screen
        composable(Screen.Profile.route) { ProfileScreen(navController) }
        // Create Post Screen
        composable(Screen.CreatePost.route) { CreatePostScreen(navController) }
        // Settings Screen
        composable(Screen.Settings.route) { SettingsScreen(navController) }
        // Search User Screen
        composable(Screen.SearchUser.route) { SearchUserScreen(navController) }
        // Comment Screen
        composable(Screen.Comment.route) { CommentScreen(navController) }
        // Messaging Screen
        composable(Screen.Message.route) { MessageScreen() } // Added Message Screen
    }
}

// Define all screens as a sealed class
sealed class Screen(val route: String) {
    object Welcome : Screen("welcome_screen")
    object SignIn : Screen("sign_in_screen")
    object SignUp : Screen("sign_up_screen")
    object Home : Screen("home_screen")
    object Profile : Screen("profile_screen")
    object CreatePost : Screen("create_post_screen")
    object Settings : Screen("settings_screen")
    object SearchUser : Screen("search_user_screen")
    object Comment : Screen("comment_screen")
    object Message : Screen("message_screen") // Added Message Screen Route
}
