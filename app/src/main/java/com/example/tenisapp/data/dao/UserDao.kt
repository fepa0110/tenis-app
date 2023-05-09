package com.example.tenisapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.tenisapp.data.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>
}