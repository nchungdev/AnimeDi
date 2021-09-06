package com.chun.domain.repository

import com.chun.domain.model.Layout

interface FirestoreRepository {
    suspend fun getHomeLayout(): List<Layout>
}