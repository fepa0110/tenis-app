package com.example.tenisapp

import com.example.tenisapp.data.TenisDatabase
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.tenisapp.data.repository.TournamentsRepository
import com.example.tenisapp.data.repository.UserRepository
import com.example.tenisapp.screens.LoginScreen
import com.example.tenisapp.screens.TournamentsScreen
import com.example.tenisapp.ui.theme.TenisAppTheme
import com.example.tenisapp.viewModel.LoginViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val itemsRepository: ItemsRepository by lazy {
            TournamentsRepository(TenisDatabase.getDatabase(context).itemDao())
        }

        super.onCreate(savedInstanceState)
        setContent {
            TenisAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {


    val navController = rememberNavController()

    val loginViewModel = LoginViewModel(onNavigateToTournaments = { navController.navigate("tournamentsList") }, userRepository)
    
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(loginViewModel)
        }
        composable("tournamentsList") {
            TournamentsScreen(modifier)
        }
        /*...*/
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TenisAppTheme {
        MyApp()
    }
}