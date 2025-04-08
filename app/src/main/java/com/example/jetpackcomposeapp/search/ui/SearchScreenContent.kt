package com.example.jetpackcomposeapp.search.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchScreenContent(
    query: String,
    results: List<String>,
    onQueryChange: (String) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(results) { result ->
                Text(text = result, modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenContentPreview() {
    SearchScreenContent(
        query = "Preview",
        results = listOf("Result 1", "Result 2", "Result 3"),
        onQueryChange = {}
    )
}
