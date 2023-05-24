package com.example.tenisapp.data

import android.content.Context
import com.example.tenisapp.data.repository.TournamentRepositoryInterface
import com.example.tenisapp.data.repository.TournamentsRepository
import com.example.tenisapp.data.repository.UsersRepository
import com.example.tenisapp.data.repository.UserRepositoryInterface
import androidx.room.RoomDatabase

interface AppContainer {
    val tournamentsRepository: TournamentRepositoryInterface
    val usersRepository: UserRepositoryInterface
}

//? Si falla probar quitando la herencia de AppContainer
class AppDataContainer(tenisDatabase: TenisDatabase) : AppContainer {
    /**
     * Implementation for [TournamentsRepository]
     */
    override val tournamentsRepository: TournamentRepositoryInterface by lazy {
        TournamentsRepository(tenisDatabase.tournamentDao())
    }

    /**
     * Implementation for [UsersRepository]
     */
    override val usersRepository: UserRepositoryInterface by lazy {
        UsersRepository(tenisDatabase.userDao())
    }
}