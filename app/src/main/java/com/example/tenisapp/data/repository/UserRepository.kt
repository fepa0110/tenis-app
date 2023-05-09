package com.example.tenisapp.data.repository

import com.example.tenisapp.data.dao.UserDao
import com.example.tenisapp.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllUsersStream(): Flow<List<User>>
}