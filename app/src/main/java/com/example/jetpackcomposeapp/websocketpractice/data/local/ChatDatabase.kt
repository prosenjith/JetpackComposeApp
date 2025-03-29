package com.example.jetpackcomposeapp.websocketpractice.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ChatMessageEntity::class], version = 1)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun chatMessageDao(): ChatMessageDao
}
