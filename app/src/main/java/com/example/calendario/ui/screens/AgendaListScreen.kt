package com.example.calendario.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button // Correct import
import androidx.compose.material3.Text    // Correct import
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.calendario.domain.model.Event

@Composable
fun AgendaListScreen(onAdd: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = onAdd) {
            Text("Adicionar")
        }
        LazyColumn {
            items(listOf<Event>()) { ev ->
                Text(ev.title)
            }
        }
    }
}
