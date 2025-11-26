package com.example.calendario.ui.state

import androidx.navigation.NavBackStackEntry

sealed class AgendaEvent {
    object Load : AgendaEvent()
    data class Add(val title: String, val description: String?, val timestamp: Long): AgendaEvent()
    data class Delete(val id: String): AgendaEvent()
}
