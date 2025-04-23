package com.example.marsphotos.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

/* private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"
    */
// UPDATE: This code has been moved to AppContainer.kt

/* private val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()
    */
// UPDATE: This code has been moved to AppContainer.kt

interface MarsApiService {
    // This defines an interface for communicating with the Mars web service.
    // It's like a blueprint for API calls.

    @GET ("photos")
    // This annotation tells Retrofit that the method below will make a GET request.
    // "photos" is the part of the URL added to the base URL to reach the specific data endpoint (e.g., baseurl.com/photos).
    // The server's response from this GET request will contain the data for this endpoint.

    suspend fun getPhotos() : List<MarsPhoto>
    // This is a suspend function, meaning it can be called from a coroutine.
    // It tells Retrofit to fetch the list of MarsPhoto objects from the "photos" endpoint.
    // UPDATE: It now returns a List of MarsPhoto objects, not a String.
}

object MarsApi {
    /*
    private val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
    */
    // UPDATE: This code has been moved to the AppContainer.kt file.
}
// This creates a singleton object named MarsApi (though its content is moved).
// A singleton means there's only one instance of it in the whole application.
//----------------------------------------------------------------------------
// Why was code moved to AppContainer.kt?
// Centralizing the setup in AppContainer.kt makes it easy to reuse the Retrofit instance and MarsApiService interface throughout the app.