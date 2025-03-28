package com.example.jetpackcomposeapp.websocketpractice

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.*
import okio.ByteString

class WebSocketManager {

    private val client = OkHttpClient()
    private lateinit var webSocket: WebSocket

    private val _messageFlow = MutableStateFlow("")
    val messageFlow = _messageFlow.asStateFlow()

    fun connect() {
        val request = Request.Builder()
            .url("wss://s14368.blr1.piesocket.com/v3/1?api_key=tUTLncZ6ie0kucIaBtdFJR1wx8EtLnXBOB6Q4aP6&notify_self=1")
            .build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {}

            override fun onMessage(webSocket: WebSocket, text: String) {
                _messageFlow.value = text
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                _messageFlow.value = bytes.utf8()
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                webSocket.close(1000, null)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                _messageFlow.value = "Error: ${t.message}"
            }
        })
    }

    fun send(message: String) {
        if (::webSocket.isInitialized) {
            webSocket.send(message)
        }
    }

    fun disconnect() {
        if (::webSocket.isInitialized) {
            webSocket.close(1000, "Goodbye")
        }
    }
}
