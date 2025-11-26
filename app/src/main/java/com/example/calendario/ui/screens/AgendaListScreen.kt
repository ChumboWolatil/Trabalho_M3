package com.example.calendario.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.calendario.data.local.EventEntity
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneId
import kotlin.collections.map

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AgendaListScreen(
    events: List<com.example.calendario.data.local.db.EventEntity>,
    onAdd: () -> Unit,
    onDelete: (String) -> Unit
) {
    val today = LocalDate.now()
    val currentMonth = YearMonth.from(today)
    val startMonth = currentMonth.minusMonths(3)
    val endMonth = currentMonth.plusMonths(3)
    val firstDayOfWeek = firstDayOfWeekFromLocale() // Or use java.time.DayOfWeek.MONDAY

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = firstDayOfWeek
    )

    val eventDates = events.map { it.toLocalDate() }.distinct()

    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {

        Button(onClick = onAdd, modifier = Modifier.fillMaxWidth()) {
            Text("Adicionar evento")
        }

        Spacer(Modifier.height(8.dp))

        HorizontalCalendar(
            state = state,
            dayContent = { day ->
                val hasEvent = eventDates.contains(day.date)
                if (day.position == DayPosition.MonthDate) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .padding(2.dp)
                            .background(color = if (hasEvent) MaterialTheme.colorScheme.primary else Color.Transparent),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = day.date.dayOfMonth.toString(),
                            color = if (hasEvent) MaterialTheme.colorScheme.onPrimary
                            else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        )

        Spacer(Modifier.height(12.dp))

        Text("Eventos:", style = MaterialTheme.typography.titleMedium)

        LazyColumn {
            items(events) { ev ->
                Text("${ev.title} - ${ev.toLocalDate()}")
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun EventEntity.toLocalDate(): LocalDate =
    java.time.Instant.ofEpochMilli(this.timestamp)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()


