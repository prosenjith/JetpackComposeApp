package com.example.jetpackcomposeapp.asynchronous

class PostRepository {
    fun fetchPostSync(): Post? {
        return try {
            val apiService = RetrofitClient.retrofit.create(PostApiService::class.java)
            val response = apiService.getPost().execute()
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            null
        }
    }
}
