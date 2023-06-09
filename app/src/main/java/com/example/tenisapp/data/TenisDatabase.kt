package com.example.tenisapp.data

import com.example.tenisapp.data.converters.Converter
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tenisapp.data.dao.TournamentDao
import com.example.tenisapp.data.dao.UserDao
import com.example.tenisapp.data.model.Tournament
import com.example.tenisapp.data.model.TournamentUser
import com.example.tenisapp.data.model.User

@Database(
    entities = [
        Tournament::class,
        User::class,
        TournamentUser::class
    ],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class TenisDatabase : RoomDatabase() {
    abstract fun tournamentDao(): TournamentDao
    abstract fun userDao(): UserDao
}