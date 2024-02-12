package com.example.nadbitov_fintech.di

import com.example.nadbitov_fintech.data.api.ApiKeyInterceptor
import com.example.nadbitov_fintech.data.api.NetworkInterface
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private const val BASE_URL = "https://kinopoiskapiunofficial.tech/"
    private const val JSON_MIME_TYPE = "application/json"

    val json by lazy {
        Json{ignoreUnknownKeys = true}
    }

    val instance: NetworkInterface by lazy {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(ApiKeyInterceptor())
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(JSON_MIME_TYPE.toMediaType()))
            .client(client)
            .build()
            .create(NetworkInterface::class.java)
    }

    @Provides
    fun provideNetworkInterface(): NetworkInterface = instance
}