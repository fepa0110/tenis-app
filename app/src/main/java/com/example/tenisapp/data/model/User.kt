package com.example.tenisapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) 
    val userId: Int= 0,

    val username: String,

    val password: String
)
