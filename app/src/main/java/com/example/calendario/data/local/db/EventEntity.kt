package com.example.calendario.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.calendario.domain.model.Event

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String?,
    val timestamp: Long
)

fun EventEntity.toDomain() = Event(id, title, description, timestamp)
fun Event.toEntity() = EventEntity(id, title, description, timestamp)
