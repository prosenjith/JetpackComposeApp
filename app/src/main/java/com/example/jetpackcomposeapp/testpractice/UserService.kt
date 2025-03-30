package com.example.jetpackcomposeapp.testpractice

import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): User
}