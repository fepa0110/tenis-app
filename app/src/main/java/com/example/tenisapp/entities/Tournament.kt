package com.example.tenisapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tournament(
    @PrimaryKey val id: Int,

    val nombre: String?,
)
