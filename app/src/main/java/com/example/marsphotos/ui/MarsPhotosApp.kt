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

@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.marsphotos.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.marsphotos.R
import com.example.marsphotos.ui.screens.HomeScreen
import com.example.marsphotos.ui.screens.MarsViewModel

@OptIn(ExperimentalMaterial3Api::class) // Opt-in required for experimental Material 3 APIs like CenterAlignedTopAppBar
@Composable
fun MarsPhotosApp() {
    // Defines how the top app bar behaves when scrolling (e.g., disappears)
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    // Scaffold provides a basic Material Design visual structure (like a place for the app bar and content)
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection), // Connects scrolling to the top app bar behavior
        topBar = { MarsTopAppBar(scrollBehavior = scrollBehavior) } // Defines the top app bar content
    ) {
        // Surface is a basic building block that takes up the full available size
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            // Gets the ViewModel using the factory defined in MarsViewModel.
            // This ensures the ViewModel survives configuration changes (like screen rotation).
            val marsViewModel: MarsViewModel = viewModel(factory = MarsViewModel.Factory)

            // Displays the main content screen based on the ViewModel's UI state.
            HomeScreen(
                marsUiState = marsViewModel.marsUiState, // Pass the current UI state
                retryAction = marsViewModel::getMarsPhotos, // Pass the function to retry fetching data
                // This sets the retryAction lambda to the ViewModel's getMarsPhotos function,
                // allowing the ErrorScreen to trigger a data reload.
                contentPadding = it, // Pass the padding provided by the Scaffold
            )
        }
    }
}

@Composable
fun MarsTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    // Creates a top app bar centered horizontally.
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior, // Links the app bar to the scroll behavior
        title = {
            // Displays the app name as the title.
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )
}
