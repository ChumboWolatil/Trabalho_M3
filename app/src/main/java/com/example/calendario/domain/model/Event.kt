package com.example.calendario.domain.model

import java.util.UUID

data class Event(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String? = null,
    val timestamp: Long
)
