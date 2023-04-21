package com.example.tenisapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tenisapp.ui.theme.TenisAppTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TenisAppTheme {
                // A surface container using the 'background' color from the theme
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

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeActivity(modifier, onNavigateToTournaments = { navController.navigate("tournamentsList") })}
        composable("tournamentsList") { TournamentsListActivity(modifier) }
        /*...*/
    }

}

@Composable
fun WelcomeActivity(
    modifier: Modifier = Modifier,
    onNavigateToTournaments: () -> Unit
    ){
    Column() {
        Text(
            text = "Bienvenido!",
            modifier = modifier
        )
        Row(){
            Button(onClick = onNavigateToTournaments) {
                Text(
                    text = "Comenzar"
                )
            }
        }
    }
}

@Composable
fun TournamentsListActivity(modifier: Modifier = Modifier){
        Column() {
        Text(
            text = "Lista!",
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TenisAppTheme {
        MyApp()
    }
}