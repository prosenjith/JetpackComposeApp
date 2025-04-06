package com.example.jetpackcomposeapp.asynchronous

import retrofit2.Call
import retrofit2.http.GET

interface PostApiService {
    @GET("posts/1")
    fun getPost(): Call<Post>
}
