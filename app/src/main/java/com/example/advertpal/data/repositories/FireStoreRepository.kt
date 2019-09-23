package com.example.advertpal.data.repositories

import android.util.Log
import com.example.advertpal.data.models.groups.Group
import com.example.advertpal.data.models.works.Work
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

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

    fun addWork(work: Work, userId: String, group : Group) {
        val workHashMap = hashMapOf(
            "groupId" to work.groupId,
            "text" to work.text,
            "periodicity" to work.periodicity,
            "groupImg" to group.photo200.toString(),
            "groupName" to group.name.toString()
        )

        db.collection(userId).add(workHashMap as Map<String, Any>)
            .addOnSuccessListener {
                Log.i("Doc", it.id)
            }
    }

    fun getWorks(groupId: String, userId: String): List<Work> {
        val works = mutableListOf<Work>()
        db.collection(userId)
            .whereEqualTo("groupId", groupId)
            .get()
            .addOnSuccessListener {
                it.documents.forEach {snapshot ->
                    works.add(
                        parseDocumentSnaphotToWork(snapshot)
                    )
                }
            }

        return works
    }

    private fun parseDocumentSnaphotToWork(snapshot: DocumentSnapshot): Work =
        Work(
            snapshot["groupId"].toString(),
            snapshot["text"].toString(),
            snapshot["periodicity"].toString()
        )


}