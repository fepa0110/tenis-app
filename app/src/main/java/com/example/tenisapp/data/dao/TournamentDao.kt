package com.example.tenisapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.tenisapp.data.model.Tournament

import kotlinx.coroutines.flow.Flow


@Dao
interface TournamentDao {
    @Query("SELECT * FROM tournament")
    fun getAll(): Flow<List<Tournament>>
}