package com.example.tenisapp.data.dao

import com.example.tenisapp.data.model.TournamentsSubcribed
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

import kotlinx.coroutines.flow.Flow

import com.example.tenisapp.data.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<User>>

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    fun findUserByUsernamePassword(username: String, password: String): Flow<User>

    @Transaction
    @Query("SELECT * FROM user")
    fun getAllTounamentsSubscriptions(): List<TournamentsSubcribed>

    //? Get tournaments subscription of userId
    /* @Transaction
    @Query("SELECT * FROM user WHERE user.id =")
    fun getTounamentsSubscribed(): List<com.example.tenisapp.data.model.TournamentsSubcribed>*/
}