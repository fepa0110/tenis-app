package com.example.tenisapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(primaryKeys = ["userId", "tournamentId"])
data class TournamentUser(
    val userId: Int,
    val tournamentId: Int
)