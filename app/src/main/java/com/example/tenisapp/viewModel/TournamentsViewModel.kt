package com.example.tenisapp.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tenisapp.data.repository.TournamentsRepository
import com.example.tenisapp.firebase.model.Tournament
import com.example.tenisapp.data.repository.TournamentRepositoryInterface
import com.example.tenisapp.firebase.service.TournamentService
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import com.example.tenisapp.states.TournamentUiState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class TournamentsViewModel(private val tournamentsRepository: TournamentRepositoryInterface,
        private val tournamentService: TournamentService
    ) : ViewModel() {

    //var tournamentList = MutableLiveData<List<Tournament>>()

    val tournamentList: MutableLiveData<List<Tournament>> by lazy {
        MutableLiveData<List<Tournament>>()
    }


    var tournamentUiState by mutableStateOf(TournamentUiState())
        private set
    init {
        viewModelScope.launch {
            getAll()
            /*tournamentUiState = tournamentUiState.copy(
                tournaments = tournamentService.getAll()
            )*/
            /*tournamentsRepository.getAllTournamentsStream().collectLatest {
                tournamentUiState = tournamentUiState.copy(
                    tournaments = it
                )
            }*/
        }
    }

    fun getAll(): LiveData<List<Tournament>> {
        tournamentService.getAllRef()
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    Log.e("TournamentsViewModel", "Ocurrio un error al obtener la lista de torneos")
                    return@addSnapshotListener
                }

                for (doc in snapshot!!) {
                    doc.getString("nombre")?.let {
                        val currentTournamentList = tournamentList.value ?: emptyList<Tournament>()

                        val newTournamentList = currentTournamentList.toMutableList()
                        newTournamentList.add(Tournament(nombre = doc.getString("nombre")!!, fecha = null))

                        tournamentList.postValue(newTournamentList)
                    }
                }

                /*if (snapshot != null) {
                    tournamentList.value = snapshot.
                }*/
            }

        return tournamentList
    }

    /*fun getAll(): LiveData<List<Tournament>> {
        return tournamentService.getAll()
    }*/

    fun updateNombre(nombre: String){
        tournamentUiState = tournamentUiState.copy(
            name = nombre
        )
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
    
    suspend fun create(tournament: Tournament) {
        tournamentService.create(tournament)
    }
}