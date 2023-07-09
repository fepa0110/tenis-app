package com.example.tenisapp.firebase.service

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tenisapp.firebase.model.Tournament
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import java.util.Date

class TournamentService(private val firebaseDatabase: FirebaseFirestore) {
    var TAG = "FirebaseDatabase --> UserService"
    var COLLECTION_NAME = "tournaments"

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

/*     suspend fun getAll() : List<Tournament> {
        var tournaments = mutableListOf<Tournament>()

        firebaseDatabase.collection(COLLECTION_NAME)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    tournaments.add(Tournament(document.data.get("nombre") as String, document.data.get("fecha") as Date))
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }

        return tournaments
    }  */
    fun getAllRef(): CollectionReference {
        return firebaseDatabase.collection(COLLECTION_NAME)
    }

}