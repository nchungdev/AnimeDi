package com.chun.data.di

import android.content.Context
import com.chun.data.BuildConfig
import com.chun.data.remote.RestApi
import com.chun.data.repo.RestRepositoryImpl
import com.chun.data.typeadapter.AnimeTypeAdapter
import com.chun.data.typeadapter.BaseObjectTypeAdapter
import com.chun.domain.model.Anime
import com.chun.domain.model.BaseObj
import com.chun.domain.repository.RestRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
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
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient, baseUrl: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideRestRepository(retrofit: Retrofit): RestRepository =
        RestRepositoryImpl(retrofit.create(RestApi::class.java))

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapter(BaseObj::class.java, BaseObjectTypeAdapter())
        .registerTypeAdapter(Anime::class.java, AnimeTypeAdapter())
        .create()

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
