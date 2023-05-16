package com.example.tenisapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tenisapp.navigation.TenisNavHost
import com.example.tenisapp.ui.theme.TenisAppTheme


/**
 * Top level composable that represents screens for the application.
 */
@Composable
fun TenisApp(navController: NavHostController = rememberNavController()) {
    TenisNavHost(navController = navController)
}

/* @Composable
fun TenisApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(onNavigateToTournaments = {navController.navigate("tournamentsList")})
        }
        composable("tournamentsList") {
            TournamentsScreen(modifier)
        }
    }

}
*/
