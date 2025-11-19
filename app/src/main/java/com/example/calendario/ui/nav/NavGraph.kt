package com.example.calendario.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calendario.ui.screens.AgendaListScreen
import com.example.calendario.ui.screens.AddEditEventScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list") {
        composable("list") { AgendaListScreen(onAdd = { navController.navigate("add") }) }
        composable("add") { AddEditEventScreen(onDone = { navController.popBackStack() }) }
    }
}
