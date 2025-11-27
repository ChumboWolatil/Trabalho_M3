
package com.example.calendario.domain.repository

import com.example.calendario.data.local.EventEntity


import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

abstract class AgendaRepository {
    abstract fun observeEvents(): Flow<List<EventEntity>>
    abstract suspend fun addEvent(event: EventEntity)
    abstract suspend fun deleteEvent(event: EventEntity)
}
