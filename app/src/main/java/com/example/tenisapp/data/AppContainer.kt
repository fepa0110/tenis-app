package com.example.tenisapp.data

import android.content.Context
import com.example.tenisapp.data.repository.TournamentRepositoryInterface
import com.example.tenisapp.data.repository.TournamentsRepository
import com.example.tenisapp.data.repository.UsersRepository
import com.example.tenisapp.data.repository.UserRepositoryInterface

interface AppContainer {
    val tournamentsRepository: TournamentRepositoryInterface
    val usersRepository: UserRepositoryInterface
}

class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [TournamentsRepository]
     */
    override val tournamentsRepository: TournamentRepositoryInterface by lazy {
        TournamentsRepository(TenisDatabase.getDatabase(context).tournamentDao())
    }

    /**
     * Implementation for [UsersRepository]
     */
    override val usersRepository: UserRepositoryInterface by lazy {
        UsersRepository(TenisDatabase.getDatabase(context).userDao())
    }
}