package com.example.tenisapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import com.example.tenisapp.data.dao.TournamentDao
import com.example.tenisapp.data.dao.UserDao
import com.example.tenisapp.data.model.Tournament
import com.example.tenisapp.data.model.User

@Database(
    entities = [
        Tournament::class,
        User::class
    ],
    version = 1
)
//@TypeConverter(Converter::class)
abstract class TenisDatabase : RoomDatabase() {
    abstract val tournamentDao: TournamentDao
    abstract val userDao: UserDao
}