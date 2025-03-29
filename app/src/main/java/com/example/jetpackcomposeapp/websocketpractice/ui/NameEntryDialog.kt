package com.example.jetpackcomposeapp.websocketpractice.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun NameEntryDialog(onNameEntered: (String) -> Unit) {
    var name by remember { mutableStateOf(TextFieldValue("")) }

    AlertDialog(
        onDismissRequest = {},
        title = { Text("Enter your name") },
        text = {
            TextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("Your name") },
                singleLine = true
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.text.isNotBlank()) {
                        onNameEntered(name.text.trim())
                    }
                }
            ) {
                Text("Start Chat")
            }
        },
        dismissButton = {}
    )
}
