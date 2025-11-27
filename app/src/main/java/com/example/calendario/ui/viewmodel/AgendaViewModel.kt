package com.example.calendario.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calendario.data.local.EventEntity
import com.example.calendario.domain.repository.AgendaRepository
import com.example.calendario.ui.state.AgendaEvent
import com.example.calendario.ui.state.AgendaState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.find
@HiltViewModel
class AgendaViewModel @Inject constructor(
    private val repo: AgendaRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AgendaState())
    val state: StateFlow<AgendaState> = _state.asStateFlow()

    init {
        process(AgendaEvent.Load)
    }

    fun process(event: AgendaEvent) {
        when (event) {
            is AgendaEvent.Load -> observe()
            is AgendaEvent.Add -> add(event)
            is AgendaEvent.Delete -> delete(event.id)
        }
    }

    private fun observe() {
        viewModelScope.launch {
            repo.observeEvents().collect { list ->
                _state.update { it.copy(events = list, isLoading = false) }
            }
        }
    }

    private fun add(e: AgendaEvent.Add) {
        viewModelScope.launch {
            val ev =
                EventEntity(title = e.title, description = e.description, timestamp = e.timestamp)
            repo.addEvent(ev)
        }
    }

    private fun delete(id: String) {
        viewModelScope.launch {
            val ev = _state.value.events.find { it.id == id } ?: return@launch
            repo.deleteEvent(ev)
        }
    }
}
