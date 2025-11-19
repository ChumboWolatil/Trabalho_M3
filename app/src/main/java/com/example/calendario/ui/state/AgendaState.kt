package com.example.calendario.ui.state

import com.example.calendario.domain.model.Event

data class AgendaState(
    val events: List<Event> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
