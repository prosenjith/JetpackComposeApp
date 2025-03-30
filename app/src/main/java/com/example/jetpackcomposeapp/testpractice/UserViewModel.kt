package com.example.jetpackcomposeapp.testpractice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _userState = MutableStateFlow<Result<User>?>(null)
    val userState: StateFlow<Result<User>?> = _userState

    fun fetchUser(userId: Int) {
        viewModelScope.launch {
            repository.getUser(userId).collect { result ->
                _userState.value = result
            }
        }
    }
}
