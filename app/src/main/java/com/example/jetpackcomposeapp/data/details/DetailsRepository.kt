package com.example.jetpackcomposeapp.data.details

import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailsRepository @Inject constructor() {
    suspend fun getItemDetails(itemId: String): String {
        delay(2000) // Simulate network delay
        return "Details for item ID: $itemId"
    }
}
