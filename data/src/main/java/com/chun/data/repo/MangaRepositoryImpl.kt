package com.chun.data.repo

import com.chun.data.remote.service.MangaService
import com.chun.domain.model.Anime
import com.chun.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MangaRepositoryImpl @Inject constructor(mangaService: MangaService) : MangaRepository {
    override suspend fun getInfo(id: Int): Flow<Anime> {
        TODO("Not yet implemented")
    }
}