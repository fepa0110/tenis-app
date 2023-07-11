package com.example.tenisapp.firebase.model

import java.util.Date

data class Game(
    val player1: String,
    val player2: String,
    var score1: Int = 0, 
    var score2: Int = 0,
    var estado: String = "Por jugar",
    val fecha: Date?
)
