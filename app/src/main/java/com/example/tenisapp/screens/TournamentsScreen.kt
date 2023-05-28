package com.example.tenisapp.screens

import TenisViewModelProvider
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.tenisapp.data.model.Tournament

import com.example.tenisapp.viewModel.TournamentsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TournamentsScreen(
    modifier: Modifier,
    viewModelProvider : TenisViewModelProvider,
) {
    val tournamentsViewModel: TournamentsViewModel = viewModelProvider.getTournamentsViewModel() as TournamentsViewModel

    val lifecycleScope = rememberCoroutineScope()

    tournamentsViewModel.tournamentUiState.tournaments

    val tournamentsList: List<Tournament> = tournamentsViewModel.tournamentUiState.tournaments

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Torneos",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
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
                }
            )
        },
        content = { innerPadding ->
            LazyColumn(contentPadding = innerPadding, modifier = Modifier.padding(vertical = 5.dp)) {
                for (aTournament in tournamentsList) {
                    item {
                        TournamentRow(aTournament.nombre)
                    }
                }
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