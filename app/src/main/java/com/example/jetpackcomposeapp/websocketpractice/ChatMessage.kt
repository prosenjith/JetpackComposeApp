package com.example.jetpackcomposeapp.websocketpractice

import android.util.Log
import com.google.gson.Gson

data class ChatMessage(
    val sender: String,
    val message: String
) {
    fun toJson(): String = Gson().toJson(this)

    companion object {
        fun fromJson(json: String): ChatMessage? {
            return try {
                Gson().fromJson(json, ChatMessage::class.java)
            } catch (e: Exception) {
                Log.e("ChatMessage", "Invalid JSON: $json", e)
                null
            }
        }
    }
}