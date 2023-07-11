package com.example.tenisapp.firebase.service

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tenisapp.firebase.model.Tournament
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import java.util.Date

class GameService(private val firebaseDatabase: FirebaseFirestore, private val tournamentService: TournamentService) {
    var TAG = "FirebaseDatabase --> GameService"
    var COLLECTION_NAME = "games"

    fun create(tournament: Tournament) {
        // Add a new document with a generated ID
        firebaseDatabase.collection(COLLECTION_NAME)
            .add(tournament)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    fun createCollection(){
        
    }

}