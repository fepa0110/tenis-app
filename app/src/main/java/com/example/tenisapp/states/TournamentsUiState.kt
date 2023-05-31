package com.example.tenisapp.states

import com.example.tenisapp.data.model.Tournament

data class TournamentUiState(
    val tournaments : List<Tournament> = listOf(),
    val name : String = ""
)

/*fun TournamentUiState.toTournament(): Tournament = Tournament(
    id = id,
    nombre = nombre
)*/

/*fun Tournament.toTournamentUiState(actionEnabled: Boolean = false): TournamentUiState = TournamentUiState(
    tournaments = listOf()
)*/

/*
fun TournamentUiState.isValid() : Boolean {
    return nombre.isNotBlank()
}
*/