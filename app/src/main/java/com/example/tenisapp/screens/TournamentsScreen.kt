package com.example.tenisapp.screens

import com.example.tenisapp.TenisViewModelProvider
import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.tenisapp.data.model.Tournament

import com.example.tenisapp.viewModel.TournamentsViewModel
import kotlinx.coroutines.launch
import java.util.Date

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TournamentsScreen(
    modifier: Modifier,
    viewModelProvider: TenisViewModelProvider,
) {
    val tournamentsViewModel: TournamentsViewModel = viewModelProvider.tournamentsViewModel

    val lifecycleScope = rememberCoroutineScope()

    // tournamentsViewModel.tournamentUiState.tournaments

    val tournamentsState = tournamentsViewModel.tournamentUiState
    // val tournamentsList: List<Tournament> = tournamentsViewModel.tournamentUiState.tournaments

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
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Localized description"
                        )
                    }
                } */
            )
        },
        content = { innerPadding ->
            LazyColumn(contentPadding = innerPadding, modifier = Modifier.padding(vertical = 5.dp)) {
                items(tournamentsState.tournaments){
                    TournamentRow(it.nombre)
                }
                /*for (aTournament in tournamentsState.tournaments) {
                    item {

                    }
                }*/

            }
            FloatingActionButton(onClick = {
                lifecycleScope.launch {
                    tournamentsViewModel.create(Tournament(nombre = "Torneo 1", fecha = Date()))
                }
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Add tournament")
            }


        }
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TournamentRow(tournamentName: String) {
    val subscribed = rememberSaveable { mutableStateOf(false) }

    //Column {
        ListItem(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary),
            headlineText = { Text(tournamentName) },
            leadingContent = {
                Icon(
                    Icons.Filled.PlayArrow,
                    contentDescription = "Localized description",
                )
            },
            trailingContent = {
                Button(
                    onClick = { subscribed.value = !subscribed.value },
                    modifier = Modifier.padding(horizontal = 6.dp)
            ) {
                Text(if (!subscribed.value) "Inscribirse" else "Inscripto")
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