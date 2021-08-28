package com.chun.anime.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.chun.anime.model.Display
import com.chun.anime.model.Section
import com.chun.domain.Resource
import com.chun.domain.data
import com.chun.domain.model.Subtype
import com.chun.domain.model.Type
import com.chun.domain.succeeded
import com.chun.domain.usecase.FetchSeasonUseCase
import com.chun.domain.usecase.FetchTopUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    fetchTopUseCase: FetchTopUseCase,
    fetchSeasonUseCase: FetchSeasonUseCase
) : ViewModel() {

    val homeItemList = liveData {
        emit(Resource.Loading)
        val topAnime =
            fetchTopUseCase(FetchTopUseCase.Params(Type.ANIME, Subtype.Anime.UPCOMING, 1))
        val movie =
            fetchTopUseCase(FetchTopUseCase.Params(Type.ANIME, Subtype.Anime.MOVIE, 1))
        val airingAnime =
            fetchTopUseCase(FetchTopUseCase.Params(Type.ANIME, Subtype.Anime.AIRING, 1))

        val tv =
            fetchTopUseCase(FetchTopUseCase.Params(Type.ANIME, Subtype.Anime.TV, 1))

        val season = fetchSeasonUseCase(FetchSeasonUseCase.Params(2021, "winter"))


        val oneshots =
            fetchTopUseCase(FetchTopUseCase.Params(Type.MANGA, Subtype.Manga.ONESHOTS, 1))
        val topManga = fetchTopUseCase(FetchTopUseCase.Params(Type.MANGA, Subtype.Manga.MANGA, 1))
        val topNovels = fetchTopUseCase(FetchTopUseCase.Params(Type.MANGA, Subtype.Manga.NOVELS, 1))

        val list = mutableListOf<Section>()
        if (topAnime.succeeded) {
            list.add(Section(Display.Portrait, "New Anime", topAnime.data!!))

        }
        if (airingAnime.succeeded) {
            list.add(Section(Display.Portrait, "Airing Anime", airingAnime.data!!))
        }
        if (season.succeeded) {
            val (name, year, animes) = season.data!!
            list.add(Section(Display.Carousel, "$name - $year", animes))
        }
        if (movie.succeeded) {
            list.add(Section(Display.Portrait, "Movie", movie.data!!))
        }
        if (topManga.succeeded) {
            list.add(Section(Display.Portrait, "Top Manga", topManga.data!!))
        }
        if (topNovels.succeeded) {
            list.add(Section(Display.Portrait, "Top Novels", topNovels.data!!))
        }
        if (tv.succeeded) {
            list.add(Section(Display.Portrait, "TV", tv.data!!))
        }
        if (oneshots.succeeded) {
            list.add(Section(Display.Portrait, "OneShot", oneshots.data!!))
        }
        if (list.isEmpty()) {
        }
        emit(Resource.Success(list))
    }
}
