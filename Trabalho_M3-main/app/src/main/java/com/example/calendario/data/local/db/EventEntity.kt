package com.example.calendario.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey val id: String = java.util.UUID.randomUUID().toString(),
    val title: String,
    val description: String?,
    val timestamp: Long
)

fun EventEntity.toDomain() = EventEntity(id, title, description, timestamp)
fun EventEntity.toEntity() = EventEntity(id, title, description, timestamp)
