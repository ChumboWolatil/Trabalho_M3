package com.example.calendario.data.local.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.calendario.data.local.db.EventEntity
import com.example.calendario.data.local.db.EventDao

@Database(
    entities = [EventEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}

