package com.example.jetpackcomposeapp.websocketpractice

class ChatRepository(private val socketManager: WebSocketManager) {
    val rawMessageFlow = socketManager.messageFlow
    fun connect() = socketManager.connect()
    fun sendMessage(message: ChatMessage) = socketManager.send(message.toJson())
    fun disconnect() = socketManager.disconnect()
}