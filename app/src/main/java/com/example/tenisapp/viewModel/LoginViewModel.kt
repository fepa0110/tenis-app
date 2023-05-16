package com.example.tenisapp.viewModel

import android.util.Patterns
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

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

// TODO: Revisar correcto pasaje de navigate
class LoginViewModel(usersRepository: UserRepositoryInterface) : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

/**
     * Holds home ui state. The list of items are retrieved from [UsersRepository] and mapped to
     * [UsersUiState]
     */
    val loginUiState: StateFlow<UsersUiState> =
                usersRepository
                    .getAllUsersStream()
                    .map { UsersUiState(it) }
                    .stateIn(
                            scope = viewModelScope,
                            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                            initialValue = UsersUiState()
                    )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidPassword(password: String): Boolean = password.length > 6

    private fun isValidEmail(email: String): Boolean  = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    suspend fun onLoginSelected() {
        _isLoading.value = true
        delay(4000)
        _isLoading.value = false
        // onNavigateToTournaments()
    }

}

/** Ui State for TournamentsScreen */
data class UsersUiState(val usersList: List<User> = listOf())