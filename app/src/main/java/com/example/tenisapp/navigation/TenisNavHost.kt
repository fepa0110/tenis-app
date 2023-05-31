package com.example.tenisapp.navigation

import com.example.tenisapp.TenisViewModelProvider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tenisapp.screens.LoginScreen
import com.example.tenisapp.screens.TournamentsScreen

/**
 * Provides Navigation graph for the application.
 */
@Composable
fun TenisNavHost(
    viewModelProvider: TenisViewModelProvider,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    // val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "login",modifier = modifier) {
        composable("login") {
            LoginScreen(onNavigateToTournaments = {navController.navigate("tournamentsList")}, viewModelProvider = viewModelProvider)
        }
        composable("tournamentsList") {
            TournamentsScreen(modifier, viewModelProvider = viewModelProvider)
        }
    }
}