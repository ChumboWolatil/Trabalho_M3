package com.example.calendario.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.calendario.data.local.EventEntity

data class Event(
    val id: String = java.util.UUID.randomUUID().toString(),
    val title: String,
    val description: String?,
    val timestamp: Long
)

fun EventEntity.toDomain() = EventEntity(id, title, description, timestamp)
fun EventEntity.toEntity() = EventEntity(id, title, description, timestamp)
