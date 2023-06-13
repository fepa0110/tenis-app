package com.example.tenisapp.viewModel

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow

import com.example.tenisapp.data.repository.UsersRepository
import com.example.tenisapp.data.model.Tournament
import com.example.tenisapp.data.model.User
import com.example.tenisapp.data.repository.UserRepositoryInterface
import com.example.tenisapp.states.UserLoginUiState

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class LoginViewModel(private val usersRepository: UserRepositoryInterface) : ViewModel() {
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

    suspend fun findUsername(username: String, password: String): User {
        //_isLoading.value = true
        _username.value = username
        _password.value = password

        var userLogin : User = User(0,"","")
        usersRepository.findUserByUsernamePassword(username,password).collect { user -> userLogin = user}

        return userLogin
    }

    suspend fun create(user: User) {
        usersRepository.create(user)
    }

}