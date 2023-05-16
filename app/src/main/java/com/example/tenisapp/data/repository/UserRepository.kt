package com.example.tenisapp.data.repository

import com.example.tenisapp.data.dao.UserDao
import com.example.tenisapp.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepositoryInterface {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllUsersStream(): Flow<List<User>>
}

class UsersRepository(private val userDao: UserDao) : UserRepositoryInterface {
    /**
     * Retrieve all the items from the the given data source.
     */
    
    override fun getAllUsersStream(): Flow<List<User>> =  userDao.getAll()

}