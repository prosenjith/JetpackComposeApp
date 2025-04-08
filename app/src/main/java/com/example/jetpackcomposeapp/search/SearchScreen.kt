package com.example.jetpackcomposeapp.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpackcomposeapp.search.ui.SearchScreenContent

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val query by viewModel.query.collectAsState()
    val results by viewModel.searchResults.collectAsState()

    SearchScreenContent(
        query = query,
        results = results,
        onQueryChange = viewModel::onQueryChanged
    )
}