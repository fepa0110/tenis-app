package com.example.tenisapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tenisapp.data.repository.TournamentsRepository
import com.example.tenisapp.data.model.Tournament
import com.example.tenisapp.data.repository.TournamentRepositoryInterface
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import com.example.tenisapp.states.TournamentUiState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TournamentsViewModel(private val tournamentsRepository: TournamentRepositoryInterface) : ViewModel() {

    var tournamentUiState by mutableStateOf(TournamentUiState())
        private set
    init {
        viewModelScope.launch {
            tournamentsRepository.getAllTournamentsStream().collect {
                tournamentUiState = tournamentUiState.copy(
                    tournaments = it
                )
            }
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
    suspend fun create(tournament: Tournament) {
        tournamentsRepository.create(tournament)
    }
}