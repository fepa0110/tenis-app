package com.example.tenisapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilledTonalIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tenisapp.TenisViewModelProvider
import com.example.tenisapp.firebase.model.Game
import java.util.Date

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamesScreen(
    navController: NavHostController,
    viewModelProvider: TenisViewModelProvider,
) {
    // val gamesViewModel: TournamentsViewModel = viewModelProvider.gamesViewModel

    val lifecycleScope = rememberCoroutineScope()

    // gamesViewModel.gameUiState.games

    // val gamesState = gamesViewModel.gameUiState

    var games = mutableListOf<Game>()

    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

/*     val nameObserver = Observer<List<Tournament>> { newList ->
        // Update the UI, in this case, a TextView
        games = newList.toMutableList()
    } */

    // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
    // gamesViewModel.gameList.observe(lifecycleOwner.value, nameObserver)

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Partidos",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                /* navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Localized description"
                        )
                    }
                } */
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {

        },
        content = { innerPadding ->
            gamesList(navController, innerPadding,games)
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primary,
                /*floatingActionButton = {
                    FloatingButton(icon = Icons.Filled.Add,
                        onClick = {
                            lifecycleScope.launch {
                                gamesViewModel.create(Tournament(nombre = "Torneo 1", fecha = Date()))
                            }
                        }
                )},*/
                contentPadding = PaddingValues(horizontal = 20.dp),
                actions = {
                    IconButton(onClick = { navigateToTournaments(navController) }) {
                        Icon(Icons.Filled.SportsTennis , contentDescription = "Tournaments")
                    }
                    FilledTonalIconToggleButton(checked = true, onCheckedChange = { }) {    
                        Icon(Icons.Filled.Scoreboard, contentDescription = "Games")
                    }
                }
            )
        }
    )

}

fun navigateToTournaments(navController: NavHostController){
    navController.navigate("tournamentsList")
}

@Composable
fun gamesList(navController: NavHostController, innerPadding: PaddingValues, games: List<Game>) {
    val games = listOf<Game>(
        Game(
            player1="Roger Federer",
            score1=25,
            player2="Rafael Nadal",
            score2=30,
            estado="En curso",
            fecha=Date()
        ),
        Game(
            player1="Novak Djokovic",
            score1=0,
            player2="André Agassi",
            score2=0,
            estado="En curso",
            fecha=Date()
        ),
        Game(
            player1="Carlos Alcaraz",
            score1=0,
            player2="Rafael Nadal",
            score2=0,
            fecha=Date()
        ),
        Game(
            player1="Casper Ruud",
            score1=0,
            player2="Francisco Cerundolo",
            score2=0,
            fecha=Date()
        )
    )

    LazyColumn(contentPadding = innerPadding, modifier = Modifier.padding(vertical = 5.dp)) {
        items(games) { game ->
            GameRow(game)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameRow(game: Game) {
        ListItem(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary),
            headlineText = {
                Text(game.player1 + " vs. " + game.player2)
            },
            supportingText = {
                Text(text = game.score1.toString() + " - " + game.score2.toString())
            },
            leadingContent = {
                Icon(
                    Icons.Filled.SportsTennis,
                    contentDescription = "",
                )
            },
            trailingContent = {
                Text(
                    text = game.estado,
                    color = if(game.estado === "En curso") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                )
            }
        )
        Divider()
}