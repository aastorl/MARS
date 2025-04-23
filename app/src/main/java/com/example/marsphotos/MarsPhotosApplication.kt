package com.example.marsphotos

import android.app.Application
import com.example.marsphotos.data.AppContainer
import com.example.marsphotos.data.DefaultAppContainer

// This class is the main entry point for the Android application.
// By extending 'Application', you tell Android to use this class to manage the app's global state.
class MarsPhotosApplication : Application() {

    // This property will hold the container that provides dependencies (like the repository).
    // 'lateinit' means it will be initialized later, before it's used.
    lateinit var container: AppContainer

    // This method is called when the application is starting.
    // It's the place to do one-time setup that's needed before any other app components run.
    override fun onCreate() {
        // Always call the superclass's onCreate() method first.
        super.onCreate()

        // Create the container that holds the app's dependencies.
        // This makes dependencies available from the moment the app starts.
        container = DefaultAppContainer()
    }
}

// What is this class for?
// Its main job is to be the starting point of your Android application.
// This lets you set up the application and initialize necessary dependencies before your app's other parts begin.
// Remember to add the line 'android:name=".MarsPhotosApplication"' in your AndroidManifest.xml file.
// The 'android:name=".MarsPhotosApplication"' line in AndroidManifest.xml is essential
// to tell Android to use this class as your application's main entry point and setup hub.