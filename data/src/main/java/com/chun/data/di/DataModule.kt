package com.chun.data.di

import android.content.Context
import com.chun.data.BuildConfig
import com.chun.data.cache.CacheProviderImpl
import com.chun.data.cache.FileCacheHelper
import com.chun.data.mapper.DataMapper
import com.chun.data.remote.service.AnimeService
import com.chun.data.remote.service.HomeService
import com.chun.data.remote.service.SearchService
import com.chun.data.remote.typeadapter.*
import com.chun.data.repo.AnimeRepositoryImpl
import com.chun.data.repo.FirestoreRepositoryImpl
import com.chun.data.repo.HomeRepositoryImpl
import com.chun.data.repo.SearchRepositoryImpl
import com.chun.domain.model.*
import com.chun.domain.repository.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideBaseUrl() = "https://api.jikan.moe/v3/"

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .cache(Cache(File(context.cacheDir, "okhttp"), 10 * 1024L * 1024L))
            .connectTimeout(20L, TimeUnit.SECONDS)
            .readTimeout(20L, TimeUnit.SECONDS)
            .addNetworkInterceptor(CacheControlInterceptor(context))
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    })
                }
            }
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        @ApplicationContext context: Context,
        gson: Gson,
        okHttpClient: OkHttpClient,
        baseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(ErrorHandlingCallAdapterFactory(context))
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideFileCache(@ApplicationContext context: Context): FileCacheHelper = FileCacheHelper(context, AppInfo())


    //
//    @Provides
//    @Singleton
//    fun provideAppDatabase(@ApplicationContext context: Context) =
//        Room.databaseBuilder(context, AppDatabase::class.java, "com.chun.animedi")
//            .build()
//
//    @Provides
//    fun provideOtakuDao(appDatabase: AppDatabase): OtakuDao = appDatabase.otakuDao()
//
//    @Provides
//    fun provideHomeDao(appDatabase: AppDatabase): HomeDao = appDatabase.homeDao()
//
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapter(Simple::class.java, SimpleTypeAdapter())
        .registerTypeAdapter(Otaku::class.java, OtakuTypeAdapter())
        .registerTypeAdapter(Aired::class.java, AiredTypeAdapter())
        .registerTypeAdapter(Prop::class.java, PropTypeAdapter())
        .registerTypeAdapter(Related::class.java, RelatedTypeAdapter())
        .registerTypeAdapter(Timestamp::class.java, TimestampTypeAdapter())

        .registerTypeAdapter(Anime::class.java, AnimeTypeAdapter())
        .registerTypeAdapter(Manga::class.java, MangaTypeAdapter())
        .create()

    @Provides
    @Singleton
    fun provideCacheProvider(fileCacheHelper: FileCacheHelper): CacheProvider = CacheProviderImpl(fileCacheHelper)

    @Provides
    @Singleton
    fun provideDataMapper(): DataMapper = DataMapper()

    @Provides
    fun provideFirestore(): FirebaseFirestore = Firebase.firestore.apply {
        firestoreSettings = firestoreSettings {
            isPersistenceEnabled = true
            cacheSizeBytes = FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED
        }
    }

    @Provides
    @Singleton
    fun provideFirebaseRepository(firestore: FirebaseFirestore, dataMapper: DataMapper): FirestoreRepository =
        FirestoreRepositoryImpl(firestore, dataMapper)

    @Provides
    @Singleton
    fun provideRestRepository(retrofit: Retrofit, cacheProvider: CacheProvider): HomeRepository =
        HomeRepositoryImpl(retrofit.create(HomeService::class.java), cacheProvider)

    @Provides
    @Singleton
    fun provideSearchRepository(retrofit: Retrofit): SearchRepository =
        SearchRepositoryImpl(retrofit.create(SearchService::class.java))

    @Provides
    @Singleton
    fun provideAnimeRepository(retrofit: Retrofit): AnimeRepository =
        AnimeRepositoryImpl(retrofit.create(AnimeService::class.java))

    class CacheControlInterceptor(context: Context) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val response = chain.proceed(request)
            val header = "public, max-age=60, max-stale=86400"
            return response.newBuilder()
                .header("Cache-Control", header)
                .build()
        }
    }
}
