package com.example.tenisapp.screens

import com.example.tenisapp.TenisViewModelProvider
import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilledTonalIconToggleButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import com.example.tenisapp.components.FloatingButton
import com.example.tenisapp.firebase.model.Tournament

import com.example.tenisapp.viewModel.TournamentsViewModel
import kotlinx.coroutines.launch
import java.util.Date

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TournamentsScreen(
    navController: NavHostController,
    viewModelProvider: TenisViewModelProvider,
) {
    val tournamentsViewModel: TournamentsViewModel = viewModelProvider.tournamentsViewModel

    val lifecycleScope = rememberCoroutineScope()

    // tournamentsViewModel.tournamentUiState.tournaments

    val tournamentsState = tournamentsViewModel.tournamentUiState

    var tournaments = mutableListOf<Tournament>()

    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    val nameObserver = Observer<List<Tournament>> { newList ->
        // Update the UI, in this case, a TextView
        tournaments = newList.toMutableList()
    }

    // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
    tournamentsViewModel.tournamentList.observe(lifecycleOwner.value, nameObserver)

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Torneos",
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
            tournamentsList(navController, innerPadding,tournaments)
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
                    FilledTonalIconToggleButton(checked = true, onCheckedChange = { }) {    
                        Icon(Icons.Filled.SportsTennis, contentDescription = "Tournaments")
                    }
                    IconButton(onClick = { navigateToGames(navController) }) {
                        Icon(
                            Icons.Filled.Scoreboard,
                            contentDescription = "Games",
                        )
                    }
                }
            )
        }
    )

}

fun navigateToTournament(navController: NavHostController, tournamentId: String){
    navController.navigate("tournamentDetail/"+tournamentId)
}

fun navigateToGames(navController: NavHostController){
    navController.navigate("gamesList")
}

@Composable
fun tournamentsList(navController: NavHostController, innerPadding: PaddingValues, tournaments: List<Tournament>) {
    LazyColumn(contentPadding = innerPadding, modifier = Modifier.padding(vertical = 5.dp)) {
        items(tournaments) { tournament ->
            TournamentRow(tournamentName = tournament.nombre) {
                navigateToTournament(
                    navController,
                    "1"
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TournamentRow(tournamentName: String, navigateToDetail: () -> Unit) {
    val subscribed = rememberSaveable { mutableStateOf(false) }

    //Column {
        ListItem(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary),
            headlineText = { Text(tournamentName) },
            leadingContent = {
                Icon(
                    Icons.Filled.KeyboardArrowRight,
                    contentDescription = "Localized description",
                )
            },
            trailingContent = {
                Button(
                    onClick = { navigateToDetail() },
                    modifier = Modifier.padding(horizontal = 6.dp)
            ) {
                Text("Ver")
            }}
        )
        Divider()
    //}

    /*Row(
        Modifier
            .background(MaterialTheme.colorScheme.secondary)
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp)
        ) {
            Text(tournamentName, style = MaterialTheme.typography.labelSmall)
        }
        Button(
            onClick = { subscribed.value = !subscribed.value },
            modifier = Modifier.padding(horizontal = 6.dp)
        ) {
            Text(if (!subscribed.value) "Inscribirse" else "Inscripto")
        }
    }*/
}