package com.example.tenisapp.data.repository

import com.example.tenisapp.data.dao.UserDao
import com.example.tenisapp.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepositoryInterface {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllUsersStream(): Flow<List<User>>

    suspend fun create(user: User)

    fun findUserByUsernamePassword(username: String, password: String): Flow<User>
}

class UsersRepository(private val userDao: UserDao) : UserRepositoryInterface {
    /**
     * Retrieve all the items from the the given data source.
     */
    
    override fun getAllUsersStream(): Flow<List<User>> =  userDao.getAll()
    
    override suspend fun create(user: User){
        userDao.insert(user);
    }

    override fun findUserByUsernamePassword(username: String, password: String): Flow<User> {
        return userDao.findUserByUsernamePassword(username, password)
    } 

}