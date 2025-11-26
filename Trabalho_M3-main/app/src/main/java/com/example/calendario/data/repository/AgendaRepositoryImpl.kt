package com.example.calendario.data.repository

import com.example.calendario.data.local.db.EventEntity
import com.example.calendario.data.local.db.EventDao
import com.example.calendario.domain.repository.AgendaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AgendaRepositoryImpl @Inject constructor(
    private val dao: EventDao
) : AgendaRepository {

    override fun observeEvents(): Flow<List<EventEntity>> {
        return dao.getEvents()
    }

    override suspend fun addEvent(event: com.example.calendario.data.local.EventEntity) {
        dao.insertEvent(event)
    }

    override suspend fun deleteEvent(event: com.example.calendario.data.local.db.EventEntity) {
        dao.deleteEvent(event)
    }
}
