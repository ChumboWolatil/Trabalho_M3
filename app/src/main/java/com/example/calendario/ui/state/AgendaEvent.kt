package com.example.calendario.ui.state



sealed class AgendaEvent {
    object Load : AgendaEvent()
    data class Add(val title: String, val description: String?, val timestamp: Long): AgendaEvent()
    data class Delete(val id: String): AgendaEvent()
}
