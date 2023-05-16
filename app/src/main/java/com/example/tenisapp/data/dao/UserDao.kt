package com.example.tenisapp.data.dao

import androidx.room.Dao
import androidx.room.Query

import kotlinx.coroutines.flow.Flow

import com.example.tenisapp.data.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<User>>
}