package com.example.connectify.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import com.example.connectify.R
import com.example.connectify.ui.theme.ConnectifyTheme
import androidx.compose.ui.tooling.preview.Preview

data class Message(val content: String, val isSentByUser: Boolean)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageScreen() {
    val messages = remember {
        mutableStateListOf(
            Message("Hey! Long time no see!", isSentByUser = false),
            Message("Yeah, it's been ages! How have you been?", isSentByUser = true),
            Message("Pretty good! Just busy with work and stuff.", isSentByUser = false),
            Message("Same here. Finally getting some free time.", isSentByUser = true)
        )
    }

    val predefinedResponses = remember {
        mapOf(
            "hello" to "Hey! What's up?",
            "how are you" to "I'm doing great, how about you?",
            "busy" to "Yeah, work has been hectic lately.",
            "weekend" to "Looking forward to it. Got any plans?",
            "party" to "Sounds fun! Count me in.",
            "movie" to "Oh, which one are you watching?",
            "dinner" to "That sounds nice. What are you having?",
            "coffee" to "Let’s grab a cup sometime soon!",
            "project" to "How’s it going? Need any help?",
            "holiday" to "Can't wait! Are you traveling anywhere?",
            "sports" to "Which game are you following these days?",
            "workout" to "Good for you! I should start again too.",
            "trip" to "That sounds amazing! Where are you headed?",
            "book" to "What’s the title? I’d love to read it.",
            "music" to "Oh, which artist are you listening to?",
            "coding" to "How’s your project coming along?",
            "family" to "That’s great. How’s everyone doing?",
            "job" to "Congrats! When do you start?",
            "vacation" to "Where are you planning to go?",
            "game" to "I’m hooked on it too!",
            "rain" to "It’s so calming, isn’t it?",
            "sunny" to "Perfect weather for a walk!",
            "food" to "I could eat that all day!",
            "date" to "That’s exciting! How did it go?",
            "pets" to "Aww, I’d love to see pictures!",
            "exercise" to "Keep it up! You’re inspiring me.",
            "funny" to "Haha, that’s hilarious!",
            "photo" to "Wow, that’s a great shot!",
            "beach" to "The best place to relax.",
            "mountains" to "So peaceful! I love hiking there.",
            "news" to "I heard about it too! Crazy, right?",
            "break" to "Sometimes you just need to unplug.",
            "travel" to "Where are you off to next?",
            "recipe" to "Yum! Share it with me?",
            "garden" to "Your plants must look amazing!",
            "weather" to "It’s been so unpredictable lately.",
            "movie night" to "Let’s plan one soon!",
            "art" to "Show me your latest masterpiece.",
            "concert" to "I’m so jealous! Who’s performing?",
            "week" to "This week has flown by.",
            "weekend plan" to "Relaxing at home sounds perfect.",
            "morning" to "Good morning! Got your coffee yet?",
            "night" to "Good night! Sweet dreams.",
            "birthday" to "Happy Birthday! Hope it’s amazing!",
            "festival" to "The decorations are so beautiful.",
            "catch up" to "We should! Let’s fix a day.",
            "game night" to "Count me in for sure!",
            "shopping" to "Find anything good?",
            "online" to "Have you tried this new app?",
            "dinner plan" to "I’m craving something spicy.",
            "study" to "All the best! You’ll ace it.",
            "team" to "Let’s meet up after the game.",
            "gym" to "Don’t skip leg day!",
            "coding issue" to "Let’s debug it together.",
            "vacay" to "Relax and enjoy!",
            "family dinner" to "Those are the best memories.",
            "coffee date" to "When are we meeting?"
        )
    }

    val newMessage = remember { mutableStateOf(TextFieldValue("")) }

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
                modifier = Modifier.fillMaxSize()
            ) {
                // Header
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "ConnectiChat",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }

                // Messages List
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(8.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(messages) { message ->
                        MessageBubble(
                            message = message,
                            senderAvatar = R.drawable.person_image,
                            receiverAvatar = R.drawable.scene_image
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                // Typing Area
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = newMessage.value,
                        onValueChange = { newMessage.value = it },
                        placeholder = { Text("Type a message...") },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary
                        )
                    )

                    Button(
                        onClick = {
                            val userMessage = newMessage.value.text.trim()
                            if (userMessage.isNotBlank()) {
                                messages.add(Message(content = userMessage, isSentByUser = true))
                                newMessage.value = TextFieldValue("")
                                val botResponse = predefinedResponses.entries
                                    .firstOrNull { userMessage.contains(it.key, ignoreCase = true) }
                                    ?.value ?: "Haha, tell me more about it!"
                                messages.add(Message(content = botResponse, isSentByUser = false))
                            }
                        },
                        shape = CircleShape,
                        modifier = Modifier.size(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = "Send",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun MessageBubble(message: Message, senderAvatar: Int, receiverAvatar: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = if (message.isSentByUser) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Receiver's Avatar (for incoming messages)
        if (!message.isSentByUser) {
            Image(
                painter = painterResource(id = receiverAvatar),
                contentDescription = "Receiver Avatar",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

        // Message Bubble
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = if (message.isSentByUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            shadowElevation = 4.dp
        ) {
            Text(
                text = message.content,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    color = if (message.isSentByUser) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier.padding(12.dp)
            )
        }

        // Sender's Avatar (for outgoing messages)
        if (message.isSentByUser) {
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = senderAvatar),
                contentDescription = "Sender Avatar",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMessageScreen() {
    ConnectifyTheme {
        MessageScreen()
    }
}
