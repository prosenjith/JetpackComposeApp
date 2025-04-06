package com.example.jetpackcomposeapp.asynchronous

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/") // Base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
