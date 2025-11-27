package com.example.calendario.data.repository

import com.example.calendario.data.local.EventEntity
import com.example.calendario.data.local.db.EventDao
import com.example.calendario.domain.repository.AgendaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AgendaRepositoryImpl @Inject constructor(
    private val dao: EventDao
) : AgendaRepository() {

    override fun observeEvents(): Flow<List<EventEntity>> {
        return dao.observeEvents()
    }

    override suspend fun addEvent(event: EventEntity) {
        dao.insert(event)
    }

    override suspend fun deleteEvent(event: EventEntity) {
        dao.delete(event)
    }
}
