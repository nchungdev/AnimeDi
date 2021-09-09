package com.chun.data.repo

import com.chun.data.mapper.DataMapper
import com.chun.domain.model.Layout
import com.chun.domain.repository.FirestoreRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.MetadataChanges
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

class FirestoreRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val dataMapper: DataMapper
) :
    FirestoreRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getHomeLayout(): List<Layout> {
        val task = firebaseFirestore.collection("home_section")
        return suspendCancellableCoroutine { continuation ->
            task.addSnapshotListener(MetadataChanges.INCLUDE) { querySnapshot, e ->
                if (querySnapshot == null) {
                    continuation.cancel(e)
                } else {
                    if (continuation.isActive) {
                        continuation.resume(querySnapshot.documents.map { dataMapper.documentToModel(it) }) {
                            continuation.cancel(it)
                        }
                    }
                }
            }
        }
    }
}
