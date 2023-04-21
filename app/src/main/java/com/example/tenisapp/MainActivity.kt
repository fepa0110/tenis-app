package com.example.tenisapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun TournamentsListActivity(
    modifier: Modifier = Modifier, 
    tournaments: List<String> = listOf("Grand Slam", "ATP Tour")){
        
    LazyColumn {
        for(tournament in tournaments){
            item{
                TournamentRow(tournament)
                Spacer(Modifier.size(12.dp))
            }
        }
    }
}

@Composable
fun TournamentRow(tournamentName: String){
    val subscribed = remember { mutableStateOf(false) }

    Row(Modifier.background(Color(0xFF6CAAFF)).fillMaxWidth().padding(vertical = 6.dp, horizontal = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){
        Text(tournamentName)
        Button(onClick = {subscribed.value = !subscribed.value }){
            Text(if(!subscribed.value) "Inscribirse" else "Inscripto")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TenisAppTheme {
        MyApp()
    }
}