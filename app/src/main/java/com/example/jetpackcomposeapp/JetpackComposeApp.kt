package com.example.jetpackcomposeapp

import android.app.Application
import androidx.room.Room
import com.example.jetpackcomposeapp.websocketpractice.data.local.ChatDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class JetpackComposeApp : Application() {

    lateinit var database: ChatDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            ChatDatabase::class.java,
            "chat_db"
        ).build()
    }
}