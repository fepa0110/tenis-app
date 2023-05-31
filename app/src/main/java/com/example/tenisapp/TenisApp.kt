package com.example.tenisapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tenisapp.navigation.TenisNavHost


/**
 * Top level composable that represents screens for the application.
 */
@Composable
fun TenisApp(viewModelProvider: TenisViewModelProvider, navController: NavHostController = rememberNavController()) {
    TenisNavHost(viewModelProvider, navController = navController)
}
