package com.example.calendario.data.remote.firebase

import com.example.calendario.domain.model.Event
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseService {
    private val db = Firebase.firestore
    private val events = db.collection("events")

    suspend fun pushEvent(e: Event) {
        events.document(e.id).set(e).await()
    }

    suspend fun deleteEvent(id: String) {
        events.document(id).delete().await()
    }
}
