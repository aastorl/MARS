/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.marsphotos.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.marsphotos.MarsPhotosApplication
import com.example.marsphotos.data.MarsPhotosRepository
import com.example.marsphotos.network.MarsPhoto
import kotlinx.coroutines.launch
import java.io.IOException

// Defines the different states of the UI (what the user sees).
// Sealed interface means only the listed subclasses can inherit from it.
sealed interface MarsUiState {
    // State when the data is successfully loaded, holding the list of photos.
    data class Success(val photos: List<MarsPhoto>) : MarsUiState
    // State when an error occurred during data fetching.
    object Error : MarsUiState
    // State when the data is currently being loaded.
    object Loading : MarsUiState
}

// ViewModel to hold and manage the UI state and data fetching.
class MarsViewModel(private val marsPhotosRepository: MarsPhotosRepository) : ViewModel() {

    // Holds the current UI state, observable by the UI.
    // 'by mutableStateOf' makes this property a State, triggering UI updates when it changes.
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set // Only the ViewModel can change this state

    init {
        // This block runs when the ViewModel is first created.
        // It immediately starts fetching Mars photos.
        getMarsPhotos()
    }
    // Calls getMarsPhotos right away to load data when the ViewModel is ready.

    // Fetches Mars photos from the repository.
    fun getMarsPhotos() {
        // Starts a coroutine in the ViewModel's scope for background work (like network calls).
        viewModelScope.launch {
            marsUiState = MarsUiState.Loading // Set state to Loading before starting the fetch
            marsUiState = try {
                // Try to fetch photos and set the state to Success if successful.
                MarsUiState.Success(marsPhotosRepository.getMarsPhotos())
            } catch (e: IOException) {
                // If an error occurs (like network issue), set the state to Error.
                MarsUiState.Error
            }
        }
    }

    // Companion object to create a Factory for the ViewModel.
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            // The initializer block defines how to create the ViewModel instance.
            initializer {
                // Get the application instance to access the dependency container.
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                // Get the repository from the application's container.
                val marsPhotosRepository = application.container.marsPhotosRepository
                // Create the ViewModel, injecting the repository it needs.
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
            }
        }
    }
    // This companion object provides a way to create the MarsViewModel, automatically providing
    // the MarsPhotosRepository dependency it needs using a ViewModel factory.
    // This is part of dependency injection.
}
