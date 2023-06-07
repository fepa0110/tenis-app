package com.example.tenisapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Tournament(
    @PrimaryKey(autoGenerate = true)
    val tournamentId: Int = 0,

    val nombre: String,
    val fecha: Date?
)
