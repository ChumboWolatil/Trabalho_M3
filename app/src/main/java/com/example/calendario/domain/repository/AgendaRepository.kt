package com.example.calendario.domain.repository

import com.example.calendario.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface AgendaRepository {
    fun observeEvents(): Flow<List<Event>>
    suspend fun addEvent(event: Event)
    suspend fun deleteEvent(event: Event)
}
