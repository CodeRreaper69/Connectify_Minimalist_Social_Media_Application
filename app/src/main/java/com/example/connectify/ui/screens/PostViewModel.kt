package com.example.connectify.ui.screens

import android.net.Uri
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel

data class Post(
    val user: String,
    val content: String,
    val imageUri: Uri?
)

class PostViewModel : ViewModel() {
    // Use StateFlow to manage the list of posts
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> get() = _posts

    // Add a new post to the list
    fun addPost(user: String, content: String, imageUri: Uri?) {
        _posts.value = _posts.value + Post(user, content, imageUri)
    }
}
