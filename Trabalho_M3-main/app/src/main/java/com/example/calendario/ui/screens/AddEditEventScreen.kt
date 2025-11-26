package com.example.calendario.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.calendario.data.local.EventEntity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditEventScreen(
    onSave: (EventEntity) -> Unit
) {
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    val datePickerState = rememberDatePickerState()

    Column(modifier = Modifier.padding(16.dp)) {

        Text("Adicionar Evento", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título do evento") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descrição (opcional)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        DatePicker(state = datePickerState)

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                val timestamp = datePickerState.selectedDateMillis ?: System.currentTimeMillis()
                onSave(
                    EventEntity(
                        title = title.text,
                        description = description.text.ifBlank { null },
                        timestamp = timestamp
                    )
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar")
        }
    }
}
