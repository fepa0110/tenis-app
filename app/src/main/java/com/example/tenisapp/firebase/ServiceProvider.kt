package com.example.tenisapp.firebase

import com.example.tenisapp.firebase.service.UserService
import com.example.tenisapp.firebase.service.TournamentService
import com.google.firebase.firestore.FirebaseFirestore

class ServiceProvider(private val firebaseDatabase: FirebaseFirestore) {
    val userService : UserService by lazy {
        UserService(firebaseDatabase)
    }

    val tournamentService : TournamentService by lazy {
        TournamentService(firebaseDatabase)
    }
}