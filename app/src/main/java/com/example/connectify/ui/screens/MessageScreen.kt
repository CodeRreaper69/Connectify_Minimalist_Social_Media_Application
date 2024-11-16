package com.example.connectify.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
        mutableStateOf(
            listOf(
                Message("Hey! What's up?", isSentByUser = false),
                Message("Its been a while, how are you?", isSentByUser = true),
                Message("Doing well, thank you!", isSentByUser = false),
                Message("Great to hear that!", isSentByUser = true)
            )
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
                    reverseLayout = true // Messages appear from the bottom
                ) {
                    items(messages.value) { message ->
                        MessageBubble(
                            message = message,
                            senderAvatar = R.drawable.person_image, // Replace with actual sender avatar
                            receiverAvatar = R.drawable.scene_image  // Replace with actual receiver avatar
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
                    // Text Field
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

                    // Send Button
                    Button(
                        onClick = {
                            if (newMessage.value.text.isNotBlank()) {
                                messages.value = messages.value + Message(
                                    newMessage.value.text,
                                    isSentByUser = true
                                )
                                newMessage.value = TextFieldValue("") // Clear input after sending
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
