package com.example.tenisapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tenisapp.data.model.Tournament

import kotlinx.coroutines.flow.Flow


@Dao
interface TournamentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tournament: Tournament)

    @Query("SELECT * FROM tournament")
    fun getAll(): Flow<List<Tournament>>
}