package com.example.tenisapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tenisapp.data.repository.TournamentsRepository
import com.example.tenisapp.data.model.Tournament
import com.example.tenisapp.data.repository.TournamentRepositoryInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TournamentsViewModel(tournamentsRepository: TournamentRepositoryInterface) : ViewModel() {

    val tournaments: Flow<List<Tournament>> = tournamentsRepository.getAllTournamentsStream();
    
}