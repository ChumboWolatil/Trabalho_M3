package com.example.calendario.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class EventEntity(
    val id: String = java.util.UUID.randomUUID().toString(),
    val title: String,
    val description: String?,
    val timestamp: Long
)
