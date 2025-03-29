package com.example.jetpackcomposeapp.websocketpractice.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeapp.websocketpractice.data.local.ChatMessageEntity
import com.example.jetpackcomposeapp.websocketpractice.data.remote.ChatRepository
import com.example.jetpackcomposeapp.websocketpractice.model.ChatMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatViewModel(
    private val userName: String,
    private val repository: ChatRepository
) : ViewModel() {

    private val _messages = MutableStateFlow<List<String>>(emptyList())
    val messages = _messages.asStateFlow()

    init {
        repository.connect()
        viewModelScope.launch {
            val localMessages = repository.getAllLocalMessages()
            _messages.value = localMessages.map {
                if (it.sender == userName) "Me: ${it.message}" else "${it.sender}: ${it.message}"
            }
        }
        viewModelScope.launch {
            repository.rawMessageFlow.collect { raw ->
                ChatMessage.fromJson(raw)?.let { msg ->
                    val display = if (msg.sender == userName) {
                        "Me: ${msg.message}"
                    } else {
                        "${msg.sender}: ${msg.message}"
                    }
                    _messages.value += display
                    repository.saveMessageToLocal(
                        ChatMessageEntity(
                            sender = msg.sender,
                            message = msg.message
                        )
                    )
                }
            }
        }
    }

    fun send(message: String) {
        val chatMessage = ChatMessage(sender = userName, message = message)
        viewModelScope.launch {
            repository.sendMessage(chatMessage)
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.disconnect()
    }
}
