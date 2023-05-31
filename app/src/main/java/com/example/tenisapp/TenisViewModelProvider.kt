package com.example.tenisapp

import androidx.lifecycle.ViewModel
import com.example.tenisapp.data.AppContainer
import com.example.tenisapp.viewModel.LoginViewModel
import com.example.tenisapp.viewModel.TournamentsViewModel

class TenisViewModelProvider(private val dataContainer: AppContainer) {
    val loginViewModel : LoginViewModel by lazy {
        LoginViewModel(dataContainer.usersRepository)
    }

    val tournamentsViewModel: TournamentsViewModel by lazy {
        TournamentsViewModel(dataContainer.tournamentsRepository)
    }
}