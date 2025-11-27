package com.example.calendario.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.calendario.data.local.EventEntity
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

// Constantes e utilitários para o calendário customizado
private val DATE_FORMATTER = DateTimeFormatter.ofPattern("MMMM yyyy", Locale("pt", "BR"))
private val LOCALE_PT_BR = Locale("pt", "BR")

@Composable
fun AgendaListScreen(
    events: List<EventEntity>,
    onAdd: () -> Unit,
    onDelete: (String) -> Unit // Mantido, mesmo que não usado no UI, para compatibilidade
) {
    val today = LocalDate.now()
    val currentMonth = YearMonth.from(today)
    val startMonth = currentMonth.minusMonths(3)
    val endMonth = currentMonth.plusMonths(3)
    val firstDayOfWeek = DayOfWeek.SUNDAY

    // Gera a lista de YearMonth para exibição horizontal
    val months = mutableListOf<YearMonth>()
    var current = startMonth
    while (!current.isAfter(endMonth)) {        months.add(current)
        current = current.plusMonths(1)
    }

    val eventDates = events.map { it.toLocalDate() }.distinct()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {

        Button(onClick = onAdd, modifier = Modifier.fillMaxWidth()) {
            Text("Adicionar evento")
        }

        Spacer(Modifier.height(8.dp))

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp), // Altura fixa para o calendário horizontal
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(months) { month ->
                MonthCalendarView(
                    month = month,
                    eventDates = eventDates,
                    firstDayOfWeek = firstDayOfWeek,
                    modifier = Modifier.width(300.dp) // Define a largura para cada mês
                )
            }
        }


        Spacer(Modifier.height(12.dp))

        Text("Eventos:", style = MaterialTheme.typography.titleMedium)

        LazyColumn {
            items(events) { ev ->

                val dateStr = ev.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                Text(
                    text = "${ev.title} - $dateStr",
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}

/**
 * Componente para renderizar um único mês em formato de grade (Calendar Grid).
 */
@Composable
private fun MonthCalendarView(
    month: YearMonth,
    eventDates: List<LocalDate>,
    firstDayOfWeek: DayOfWeek,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(4.dp)) {
        // Título do Mês
        Text(
            text = month.format(DATE_FORMATTER).replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = TextAlign.Center
        )

        // Cabeçalho dos dias da semana (D, S, T, Q, Q, S, S)
        Row(modifier = Modifier.fillMaxWidth()) {
            val daysOfWeek = DayOfWeek.values().map { it }
            // Reordena os dias para começar na 'firstDayOfWeek'
            val orderedDays = (0 until 7).map { index ->
                daysOfWeek[(firstDayOfWeek.ordinal + index) % 7]
            }

            orderedDays.forEach { dayOfWeek ->
                Text(
                    text = dayOfWeek.getDisplayName(TextStyle.NARROW, LOCALE_PT_BR),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }

        Spacer(Modifier.height(4.dp))

        // Geração dos dias do mês
        val firstDayOfMonth = month.atDay(1)
        val lastDayOfMonth = month.atEndOfMonth()

        // Calcula quantos dias de preenchimento (padding) são necessários no início
        val startPaddingDays = (firstDayOfMonth.dayOfWeek.ordinal - firstDayOfWeek.ordinal + 7) % 7
        val totalDaysInCalendar = startPaddingDays + lastDayOfMonth.dayOfMonth

        val dayItems = mutableListOf<LocalDate?>()

        // Adiciona dias de preenchimento (null)
        repeat(startPaddingDays) {
            dayItems.add(null)
        }

        // Adiciona os dias do mês
        (1..lastDayOfMonth.dayOfMonth).forEach { dayOfMonth ->
            dayItems.add(month.atDay(dayOfMonth))
        }

        // Renderiza os dias em linhas de 7
        val chunkedDays = dayItems.chunked(7)
        chunkedDays.forEach { week ->
            Row(modifier = Modifier.fillMaxWidth()) {
                repeat(7) { index ->
                    val dayDate = week.getOrElse(index) { null }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f) // Garante que o dia seja um quadrado
                            .padding(2.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        dayDate?.let { date ->
                            val hasEvent = eventDates.contains(date)
                            val isToday = date == LocalDate.now()

                            // O Box que representa o dia
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        color = when {
                                            hasEvent -> MaterialTheme.colorScheme.primary
                                            isToday -> MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)
                                            else -> Color.Transparent
                                        },
                                        shape = MaterialTheme.shapes.small
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = date.dayOfMonth.toString(),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = when {
                                        hasEvent -> MaterialTheme.colorScheme.onPrimary
                                        isToday -> MaterialTheme.colorScheme.onSecondaryContainer
                                        else -> MaterialTheme.colorScheme.onSurface
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Extensão para converter EventEntity para LocalDate (mantida a original).
 */
private fun EventEntity.toLocalDate(): LocalDate =
    java.time.Instant.ofEpochMilli(this.timestamp)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()