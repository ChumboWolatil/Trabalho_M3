package com.example.calendario.ui.state

import com.example.calendario.data.local.db.EventEntity



data class AgendaState(
    val events: List<EventEntity> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
