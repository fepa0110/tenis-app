package com.example.tenisapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.tenisapp.screens.LoginScreen
import com.example.tenisapp.screens.TournamentsScreen

/**
 * Provides Navigation graph for the application.
 */
@Composable
fun TenisNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    // val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "login",modifier = modifier) {
        composable("login") {
            LoginScreen(onNavigateToTournaments = {navController.navigate("tournamentsList")})
        }
        composable("tournamentsList") {
            TournamentsScreen(modifier)
        }
        /*...*/
    }
}