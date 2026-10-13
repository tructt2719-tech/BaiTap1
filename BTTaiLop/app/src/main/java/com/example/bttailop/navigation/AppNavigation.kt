package com.example.bttailop.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.bttailop.screens.HelloWorldScreen

class NavController(initialRoute: String) {
    var currentRoute by mutableStateOf(initialRoute)
        private set

    fun navigate(route: String) {
        currentRoute = route
    }

    fun popBackStack() {
        // Reserved for back navigation in future tasks
    }
}

@Composable
fun rememberNavController(startDestination: String = "hello"): NavController {
    return remember { NavController(startDestination) }
}

@Composable
fun NavHost(
    navController: NavController,
    startDestination: String
) {
    when (navController.currentRoute) {
        "hello" -> HelloWorldScreen()
        else -> HelloWorldScreen()
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController("hello")
    NavHost(
        navController = navController,
        startDestination = "hello"
    )
}
