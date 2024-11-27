package com.example.connectify.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.connectify.R
import com.example.connectify.ui.navigation.Screen
import com.example.connectify.ui.theme.ConnectifyTheme

@Composable
fun HomeScreen(navController: NavController, postViewModel: PostViewModel = viewModel()) {
    val posts = postViewModel.posts.collectAsState().value

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
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
            ) {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Title
                        Text(
                            text = "Home",
                            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        // Navigation Buttons
                        NavigationButton(
                            text = "Create Post",
                            onClick = { navController.navigate(Screen.CreatePost.route) },
                            color = MaterialTheme.colorScheme.primary
                        )
                        NavigationButton(
                            text = "Comment on Post",
                            onClick = { navController.navigate(Screen.Comment.route) },
                            color = MaterialTheme.colorScheme.primary
                        )
                        NavigationButton(
                            text = "View Profile",
                            onClick = { navController.navigate(Screen.Profile.route) },
                            color = MaterialTheme.colorScheme.secondary
                        )
                        NavigationButton(
                            text = "Search User",
                            onClick = { navController.navigate(Screen.SearchUser.route) },
                            color = MaterialTheme.colorScheme.secondary
                        )
                        NavigationButton(
                            text = "ConnectiChat",
                            onClick = { navController.navigate(Screen.Message.route) },
                            color = MaterialTheme.colorScheme.secondary
                        )
                        NavigationButton(
                            text = "Settings",
                            onClick = { navController.navigate(Screen.Settings.route) },
                            color = MaterialTheme.colorScheme.secondaryContainer
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Example Posts",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                // Example Posts
                items(
                    listOf(
                        Post("Sourabh", "Check out this amazing cat!",imageUri = null, drawableRes = R.drawable.scene_image),
                        Post("Malisa", "Enjoying a wonderful day!",imageUri = null, drawableRes = R.drawable.food_image),
                        Post("Ron", "Excited to be here!",imageUri = null, drawableRes = R.drawable.person_image)
                    )
                ) { post ->
                    ExamplePostWithImage(
                        user = post.user,
                        content = post.content,
                        imageResId = post.drawableRes // Pass the drawable resource ID
                    )
                }


                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Recent Posts",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                // User Posts
                items(posts) { post ->
                    PostItem(post)
                }
            }
        }
    }
}

@Composable
fun NavigationButton(text: String, onClick: () -> Unit, color: Color) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(bottom = 8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun PostItem(post: Post) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = post.user,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Show image from URI or drawable resource
            when {
                post.imageUri != null -> {
                    Image(
                        painter = rememberAsyncImagePainter(model = post.imageUri),
                        contentDescription = "Post Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                post.drawableRes != null -> {
                    Image(
                        painter = painterResource(id = post.drawableRes),
                        contentDescription = "Post Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                else -> {
                    Image(
                        painter = painterResource(id = R.drawable.ic_placeholder),
                        contentDescription = "Placeholder Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = post.content,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}


@Composable
fun ExamplePostWithImage(user: String, content: String, imageResId: Int?) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = user,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))

            if (imageResId != null) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "Post Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}


@Preview(showBackground = true, name = "Dark Mode")
@Composable
fun PreviewHomeScreen() {
    ConnectifyTheme {
        HomeScreen(navController = rememberNavController())
    }
}
