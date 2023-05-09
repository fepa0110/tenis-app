package com.example.tenisapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tenisapp.data.dao.TournamentDao
import com.example.tenisapp.data.dao.UserDao
import com.example.tenisapp.data.model.Tournament
import com.example.tenisapp.data.model.User

@Database(
    entities = [
        Tournament::class,
        User::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TenisDatabase : RoomDatabase() {
    abstract fun tournamentDao(): TournamentDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var Instance: TenisDatabase? = null

        fun getDatabase(context: Context): TenisDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, TenisDatabase::class.java, "tenis")
                    .build()
                    .also { Instance = it }
            }
        }
    }

}