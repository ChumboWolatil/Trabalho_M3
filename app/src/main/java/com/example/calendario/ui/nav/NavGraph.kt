package com.example.calendario.ui.nav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calendario.data.local.EventEntity
import com.example.calendario.ui.screens.AddEditEventScreen
import com.example.calendario.ui.screens.AgendaListScreen
import com.example.calendario.ui.state.AgendaEvent
import com.example.calendario.ui.viewmodel.AgendaViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list") {

        composable("list") {
            val vm: AgendaViewModel = hiltViewModel()
            val state by vm.state.collectAsState()

            AgendaListScreen(
                events = state.events,
                onAdd = { navController.navigate("add") },
                onDelete = { vm.process(AgendaEvent.Delete(it)) }
            )
        }

        composable("add") {
            val vm: AgendaViewModel = hiltViewModel()
            val ev = EventEntity(
                title = "",
                description = "",
                timestamp = 0
            );
            AddEditEventScreen(
                onSave = { ev->
                    vm.process(AgendaEvent.Add(ev.title, ev.description, ev.timestamp))
                    navController.popBackStack()
                }
            )
        }
    }
}


