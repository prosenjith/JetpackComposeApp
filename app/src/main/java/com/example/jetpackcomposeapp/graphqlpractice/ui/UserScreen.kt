package com.example.jetpackcomposeapp.graphqlpractice.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackcomposeapp.graphqlpractice.model.UserData
import com.example.jetpackcomposeapp.graphqlpractice.viewmodel.UserViewModel

@Composable
fun UserScreen(
    viewModel: UserViewModel = viewModel(),
    onSeePostsClick: (userId: String) -> Unit
) {
    var userIdInput by remember { mutableStateOf("") }
    val user by viewModel.user.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = userIdInput,
            onValueChange = { userIdInput = it },
            label = { Text("Enter User ID") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                if (userIdInput.isNotBlank()) {
                    viewModel.fetchUser(userIdInput)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Search User")
        }
        Spacer(modifier = Modifier.height(24.dp))
        if (user != null) {
            UserCard(user = user!!, onSeePostsClick = { onSeePostsClick(user!!.id!!) })
        } else if (viewModel.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.size(32.dp))
            }
        }
    }
}

@Composable
fun UserCard(user: UserData, onSeePostsClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = user.name.orEmpty(), style = MaterialTheme.typography.headlineSmall)
            Text(text = "Email: ${user.email}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Phone: ${user.phone}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Website: ${user.website}", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onSeePostsClick,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("See Posts")
            }
        }
    }
}
