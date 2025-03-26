package com.example.jetpackcomposeapp.graphqlpractice.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackcomposeapp.graphqlpractice.model.PostData
import com.example.jetpackcomposeapp.graphqlpractice.viewmodel.UserViewModel

@Composable
fun PostsScreen(userId: String, viewModel: UserViewModel = viewModel()) {
    val posts by viewModel.posts.collectAsState()

    LaunchedEffect(userId) {
        viewModel.fetchPosts(userId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Posts by User $userId", style = MaterialTheme.typography.headlineSmall)

        if (viewModel.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (posts.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No posts found.")
            }
        } else {
            LazyColumn {
                items(posts) { post ->
                    PostCard(post = post)
                }
            }
        }
    }
}

@Composable
fun PostCard(post: PostData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Post #${post.id}", style = MaterialTheme.typography.labelMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = post.title.orEmpty(), style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = post.body.orEmpty(), style = MaterialTheme.typography.bodyMedium)
        }
    }
}
