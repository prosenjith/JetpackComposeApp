package com.example.jetpackcomposeapp.search

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepository @Inject constructor() {

    fun search(query: String): Flow<List<String>> = flow {
        delay(1000) // Simulate API/network delay
        val fakeResults = List(5) { "$query result ${it + 1}" }
        emit(fakeResults)
    }
}
