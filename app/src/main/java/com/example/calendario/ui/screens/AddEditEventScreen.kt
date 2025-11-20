package com.example.calendario.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button // Correção: Importe o Button do material3
import androidx.compose.material3.Text    // Correção: Importe o Text do material3
import androidx.compose.runtime.Composable

@Composable
fun AddEditEventScreen(onDone: () -> Unit) {
    Column {
        Text("Tela de adicionar evento (placeholder)")
        Button(onClick = onDone) {
            Text("Salvar")
        }
    }
}
