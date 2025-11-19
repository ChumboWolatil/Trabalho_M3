package com.example.calendario.data.repository

import com.example.calendario.data.local.db.EventDao
import com.example.calendario.data.local.db.toDomain
import com.example.calendario.data.local.db.toEntity
import com.example.calendario.data.remote.firebase.FirebaseService
import com.example.calendario.domain.model.Event
import com.example.calendario.domain.repository.AgendaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AgendaRepositoryImpl(
    private val dao: EventDao,
    private val firebase: FirebaseService
): AgendaRepository {
    override fun observeEvents(): Flow<List<Event>> = dao.getAll().map { list -> list.map { it.toDomain() } }

    override suspend fun addEvent(event: Event) {
        dao.insert(event.toEntity())
        try { firebase.pushEvent(event) } catch (_: Exception) {}
    }

    override suspend fun deleteEvent(event: Event) {
        dao.delete(event.toEntity())
        try { firebase.deleteEvent(event.id) } catch (_: Exception) {}
    }
}
