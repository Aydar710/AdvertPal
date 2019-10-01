package com.example.advertpal.data.repositories

import android.util.Log
import com.example.advertpal.data.models.groups.Group
import com.example.advertpal.data.models.works.Work
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single

class FireStoreRepository {

    private val db = FirebaseFirestore.getInstance()

    private var maxId: Int = 1

    init {
        /*db.addSnapshotListener { snapshot, e ->
            if (snapshot != null && !snapshot.isEmpty) {
                maxId = snapshot.documents.size
            }
        }*/
    }

    fun addWork(work: Work, userId: String) {

        db.collection(userId)
            .add(work)
            .addOnSuccessListener {
                Log.i("Doc", it.id)
            }
    }

    fun getWorks(userId: String): Single<List<Work>> {
        return Single.create { singleEmitter ->
            db.collection(userId)
                .get()
                .addOnSuccessListener {
                    val works = mutableListOf<Work>()
                    for (document in it) {
                        val groupHashMap = document?.get("group") as HashMap<String, Any>
                        val group = groupHashMap.toGroup()

                        val work = Work(
                            text = document.get("text") as String,
                            periodicity = document.get("periodicity") as String,
                            group = group
                        )

                        works.add(work)
                    }
                    singleEmitter.onSuccess(works)
                }
                .addOnFailureListener {
                    singleEmitter.onError(it)
                }
        }
    }

    private fun parseDocumentSnapshotToWork(snapshot: DocumentSnapshot): Work =
        Work(
            snapshot["groupId"].toString(),
            snapshot["text"].toString(),
            snapshot["group"] as Group
        )

    fun HashMap<String, Any>.toGroup(): Group =
        Group(
            id = this["id"] as Long,
            name = this["name"] as String,
            photo50 = this["photo50"] as String,
            photo100 = this["photo100"] as String,
            photo200 = this["photo200"] as String

        )
}