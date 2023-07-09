package com.example.tenisapp

import androidx.lifecycle.ViewModel
import com.example.tenisapp.data.AppContainer
import com.example.tenisapp.firebase.ServiceProvider
import com.example.tenisapp.viewModel.LoginViewModel
import com.example.tenisapp.viewModel.TournamentsViewModel

class TenisViewModelProvider(private val dataContainer: AppContainer, private val serviceProvider: ServiceProvider) {
    val loginViewModel : LoginViewModel by lazy {
        LoginViewModel(dataContainer.usersRepository,serviceProvider.userService)
    }

    val tournamentsViewModel: TournamentsViewModel by lazy {
        TournamentsViewModel(dataContainer.tournamentsRepository, serviceProvider.tournamentService)
    }
}