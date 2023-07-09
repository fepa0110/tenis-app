package com.example.tenisapp.states

import com.example.tenisapp.firebase.model.Tournament

data class TournamentUiState(
    val tournaments : List<Tournament> = mutableListOf<Tournament>(),
    val name : String = ""
)

/*fun TournamentUiState.toTournament(): com.example.tenisapp.firebase.model.Tournament = com.example.tenisapp.firebase.model.Tournament(
    id = id,
    nombre = nombre
)*/

/*fun com.example.tenisapp.firebase.model.Tournament.toTournamentUiState(actionEnabled: Boolean = false): TournamentUiState = TournamentUiState(
    tournaments = listOf()
)*/

/*
fun TournamentUiState.isValid() : Boolean {
    return nombre.isNotBlank()
}
*/