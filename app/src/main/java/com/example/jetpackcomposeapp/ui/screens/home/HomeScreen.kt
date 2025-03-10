package com.example.jetpackcomposeapp.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeapp.ui.navigation.Screen

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Home Screen", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate(Screen.Details.withArgs("42")) }) {
            Text("Go to Details")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { navController.navigate(Screen.About.route) }) {
            Text("Go to About")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { navController.navigate(Screen.Contact.route) }) {
            Text("Go to Contact")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navController = rememberNavController()) // Mock NavController for preview
}
