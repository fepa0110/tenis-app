package com.example.tenisapp.firebase.service

import android.util.Log
import com.example.tenisapp.firebase.model.User
import com.google.firebase.firestore.FirebaseFirestore

class UserService(private val firebaseDatabase: FirebaseFirestore) {
    var TAG = "FirebaseDatabase --> UserService"
    var COLLECTION_NAME = "users"

    fun createUser(user: User) {
        // Add a new document with a generated ID
        firebaseDatabase.collection(COLLECTION_NAME)
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    fun getUserByUsername(username: String) : User? {
        var user : User? = null

        firebaseDatabase.collection(COLLECTION_NAME)
            .whereEqualTo("username", username)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    user = User(document.data.get("username") as String, document.data.get("password") as String)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

        return user
    }
}