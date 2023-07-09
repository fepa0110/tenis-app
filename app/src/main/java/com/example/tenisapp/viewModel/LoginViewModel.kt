package com.example.tenisapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.tenisapp.firebase.model.User
import com.example.tenisapp.data.repository.UserRepositoryInterface
import com.example.tenisapp.firebase.service.UserService

import kotlinx.coroutines.delay

class LoginViewModel(private val usersRepository: UserRepositoryInterface,
            private val userService: UserService
) : ViewModel() {
    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onLoginChanged(username: String, password: String) {
        _username.value = username
        _password.value = password
        _loginEnable.value = isValidUsername(username) && isValidPassword(password)
    }

    private fun isValidPassword(password: String): Boolean = password.length > 3

    private fun isValidUsername(username: String): Boolean  = username.length > 3

    suspend fun onLoginSelected() {
        _isLoading.value = true
        delay(4000)
        _isLoading.value = false
        // onNavigateToTournaments()
    }

    fun findUsername(username: String, password: String) : User? {
        return userService.getUserByUsername(username)
    }

}