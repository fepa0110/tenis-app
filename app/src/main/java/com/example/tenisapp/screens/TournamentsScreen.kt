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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tenisapp.ui.theme.TenisAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TournamentsScreen(
    modifier: Modifier,
    tournaments: List<String> = listOf("Grand Slam", "ATP Tour")
) {
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
                for (tournament in tournaments) {
                    item {
                        TournamentRow(tournament)
                        Spacer(Modifier.size(12.dp))
                    }
                }
            }
        }
    )

}

@Composable
fun TournamentRow(tournamentName: String) {
    val subscribed = rememberSaveable { mutableStateOf(false) }

    Row(
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
    }
}