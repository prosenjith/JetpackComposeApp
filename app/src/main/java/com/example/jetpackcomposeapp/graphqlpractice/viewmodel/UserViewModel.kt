package com.example.jetpackcomposeapp.graphqlpractice.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeapp.graphqlpractice.data.UserRepository
import com.example.jetpackcomposeapp.graphqlpractice.model.PostData
import com.example.jetpackcomposeapp.graphqlpractice.model.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val repository = UserRepository()

    var isLoading by mutableStateOf(false)
        private set

    private val _user = MutableStateFlow<UserData?>(null)
    val user = _user.asStateFlow()

    private val _posts = MutableStateFlow<List<PostData>>(emptyList())
    val posts = _posts.asStateFlow()

    fun fetchUser(userId: String) {
        viewModelScope.launch {
            isLoading = true
            repository.getUser(userId).collect { userData ->
                _user.value = userData
                isLoading = false
            }
        }
    }

    fun fetchPosts(userId: String) {
        viewModelScope.launch {
            isLoading = true
            repository.getUserPosts(userId).collect {
                _posts.value = it
                isLoading = false
            }
        }
    }
}
