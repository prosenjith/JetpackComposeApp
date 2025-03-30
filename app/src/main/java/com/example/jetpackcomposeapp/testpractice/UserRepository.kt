package com.example.jetpackcomposeapp.testpractice

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class UserRepository(private val apiService: UserService) {
    fun getUser(userId: Int): Flow<Result<User>> =
        flow {
            val user = apiService.getUser(userId)
            emit(Result.success(user))
        }.catch { e ->
            emit(Result.failure(e))
        }
}
