package com.example.jetpackcomposeapp.websocketpractice.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcomposeapp.websocketpractice.data.local.ChatMessageDao
import com.example.jetpackcomposeapp.websocketpractice.data.remote.ChatRepository
import com.example.jetpackcomposeapp.websocketpractice.data.remote.WebSocketManager

class ChatViewModelFactory(
    private val userName: String,
    private val chatDao: ChatMessageDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = ChatRepository(WebSocketManager(), chatDao)
        return ChatViewModel(userName, repository) as T
    }
}
