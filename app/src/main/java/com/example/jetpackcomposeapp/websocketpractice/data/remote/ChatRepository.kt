package com.example.jetpackcomposeapp.websocketpractice.data.remote

import com.example.jetpackcomposeapp.websocketpractice.data.local.ChatMessageDao
import com.example.jetpackcomposeapp.websocketpractice.data.local.ChatMessageEntity
import com.example.jetpackcomposeapp.websocketpractice.model.ChatMessage

class ChatRepository(
    private val socketManager: WebSocketManager,
    private val chatDao: ChatMessageDao
) {
    val rawMessageFlow = socketManager.messageFlow

    fun connect() = socketManager.connect()

    fun sendMessage(message: ChatMessage) {
        socketManager.send(message.toJson())
    }

    fun disconnect() = socketManager.disconnect()

    suspend fun saveMessageToLocal(entity: ChatMessageEntity) {
        chatDao.insertMessage(entity)
    }

    suspend fun getAllLocalMessages(): List<ChatMessageEntity> {
        return chatDao.getAllMessages()
    }
}
