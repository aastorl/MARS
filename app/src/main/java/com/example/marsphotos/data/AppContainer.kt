package com.example.marsphotos.data

import com.example.marsphotos.network.MarsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 * Interface defining the dependencies provided by the application container.
 */
interface AppContainer {
    /**
     * Provides an instance of [MarsPhotosRepository].
     */
    val marsPhotosRepository : MarsPhotosRepository
}

/**
 * Default implementation of [AppContainer] that configures and provides
 * dependencies using Retrofit for network operations.
 */
class DefaultAppContainer : AppContainer {

    // Base URL for the Mars photos API.
    // Note: This is now a class property, not a top-level constant.
    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com"

    /**
     * Configured Retrofit instance for making network requests.
     */
    private val retrofit = Retrofit.Builder()
        // Add a converter factory to handle JSON responses using kotlinx.serialization.
        // This replaces the previous ScalarsConverterFactory as JSON is now expected.
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        // Set the base URL for all API requests.
        .baseUrl(baseUrl)
        // Build the Retrofit client with the specified configurations.
        .build()

    /**
     * Lazily creates an instance of the [MarsApiService] interface using Retrofit.
     * 'lazy' ensures the instance is created only when first accessed.
     * It's private because it's an internal implementation detail used by the repository.
     */
    private val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }

    /**
     * Lazily creates and provides the [NetworkMarsPhotosRepository] implementation.
     * This fulfills the [AppContainer]'s requirement for a [MarsPhotosRepository].
     * It depends on the [retrofitService] to perform network operations.
     */
    override val marsPhotosRepository: MarsPhotosRepository by lazy {
        NetworkMarsPhotosRepository(retrofitService)
    }
}