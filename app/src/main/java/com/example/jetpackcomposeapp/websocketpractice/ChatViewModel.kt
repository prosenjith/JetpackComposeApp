package com.example.jetpackcomposeapp.websocketpractice

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatViewModel(private val userName: String) : ViewModel() {

    private val repository = ChatRepository(WebSocketManager())
    private val _messages = MutableStateFlow<List<String>>(emptyList())
    val messages = _messages.asStateFlow()

    init {
        repository.connect()
        viewModelScope.launch {
            repository.rawMessageFlow.collect { raw ->
                Log.d("ChatViewModel", "Raw message: $raw")
                ChatMessage.fromJson(raw)?.let { msg ->
                    val display = if (msg.sender == userName) {
                        "Me: ${msg.message}"
                    } else {
                        "${msg.sender}: ${msg.message}"
                    }
                    _messages.value += display
                }
            }
        }
    }

    fun send(message: String) {
        val chatMessage = ChatMessage(sender = userName, message = message)
        repository.sendMessage(chatMessage)
    }

    override fun onCleared() {
        super.onCleared()
        repository.disconnect()
    }
}
