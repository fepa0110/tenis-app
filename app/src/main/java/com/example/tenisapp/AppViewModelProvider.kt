package com.example.tenisapp

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tenisapp.viewModel.TournamentsViewModel
import com.example.tenisapp.viewModel.LoginViewModel

/** Provides Factory to create instance of ViewModel for the entire Inventory app */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for HomeViewModel
        initializer { TournamentsViewModel(tenisApplication().container.tournamentsRepository) }
        initializer { LoginViewModel(tenisApplication().container.usersRepository) }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [TenisApplication].
 */
fun CreationExtras.tenisApplication(): TenisApplication =
        (this[AndroidViewModelFactory.APPLICATION_KEY] as TenisApplication)