
package com.example.calendario.domain.repository

import com.example.calendario.data.local.db.EventEntity

import kotlinx.coroutines.flow.Flow

interface AgendaRepository {
    fun observeEvents(): Flow<List<EventEntity>>
    suspend fun addEvent(event: com.example.calendario.data.local.EventEntity)
    suspend fun deleteEvent(event: EventEntity)
}
