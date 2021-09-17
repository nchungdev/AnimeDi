package com.chun.domain.repository

import com.chun.domain.model.Layout

interface FirestoreRepository {
    suspend fun getLayout(section: String): List<Layout>
}