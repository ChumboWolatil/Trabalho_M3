package com.example.calendario.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun AddEditEventScreen(onDone: () -> Unit) {
    Column {
        Text("Tela de adicionar evento (placeholder)")
        Button(onClick = onDone) { Text("Salvar") }
    }
}
