package com.example.tenisapp.screens

import com.example.tenisapp.TenisViewModelProvider
import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tenisapp.components.FloatingButton
import com.example.tenisapp.components.PrimaryButton
import com.example.tenisapp.firebase.model.Game
import com.example.tenisapp.firebase.model.Tournament

import com.example.tenisapp.viewModel.TournamentsViewModel
import kotlinx.coroutines.launch
import java.util.Date

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TournamentDetailScreen(
    tournamentId: String,
    viewModelProvider: TenisViewModelProvider,
) {
    val tournamentsViewModel: TournamentsViewModel = viewModelProvider.tournamentsViewModel

    val lifecycleScope = rememberCoroutineScope()

    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

/*     val nameObserver = Observer<List<Tournament>> { newList ->
        // Update the UI, in this case, a TextView
        tournaments = newList.toMutableList()
    }

    // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
    tournamentsViewModel.tournamentList.observe(lifecycleOwner.value, nameObserver) */

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Torneo",
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
        content = { innerPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ){
                gamesList(innerPadding)
                PrimaryButton(text = "Subscrirse", onClick = { /* */ }, enabled = true)
            }
        }
    )

}

@Composable
fun gamesList(innerPadding: PaddingValues) {
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
            player1="Casper Ruud",
            score1=0,
            player2="Francisco Cerundolo",
            score2=0,
            fecha=Date()
        )
    )
    LazyColumn(contentPadding = innerPadding, modifier = Modifier.padding(vertical = 5.dp)) {
        items(games) { game ->
            GameDetailRow(gameName = game.player1 + " vs. " + game.player2)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailRow(gameName: String) {
        ListItem(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary),
            headlineText = { Text(gameName) },
            leadingContent = {
                Icon(
                    Icons.Filled.SportsTennis,
                    contentDescription = "",
                )
            }
        )
        Divider()
}