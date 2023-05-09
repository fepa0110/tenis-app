package com.example.tenisapp.data

import android.content.Context
import com.example.tenisapp.data.repository.TournamentsRepository

interface AppContainer {
    val tournamentsRepository: TournamentsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ItemsRepository]
     */
    override val tournamentsRepository: TournamentsRepository by lazy {
        TournamentsRepository(TenisDatabase.getDatabase(context).tournamentDao())
    }
}