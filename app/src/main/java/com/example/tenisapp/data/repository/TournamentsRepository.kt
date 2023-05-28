package com.example.tenisapp.data.repository

import androidx.lifecycle.LiveData
import com.example.tenisapp.data.dao.TournamentDao
import com.example.tenisapp.data.model.Tournament
import kotlinx.coroutines.flow.Flow

interface TournamentRepositoryInterface {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllTournamentsStream(): Flow<List<Tournament>>
    suspend fun create(tournament: Tournament)
}

class TournamentsRepository(private val tournamentDao: TournamentDao) : TournamentRepositoryInterface{
    override fun getAllTournamentsStream() = tournamentDao.getAll()

    override suspend fun create(tournament: Tournament){
        tournamentDao.insert(tournament);
    }

}
