package com.example.marsphotos.fake.rules

import com.example.marsphotos.fake.FakeDataSource
import com.example.marsphotos.fake.FakeNetworkMarsPhotosRepository
import com.example.marsphotos.ui.screens.MarsUiState
import com.example.marsphotos.ui.screens.MarsViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

// This is a test class for the MarsViewModel.
class MarsViewModelTest {

    // This JUnit Rule uses our custom TestDispatcherRule to control coroutine dispatchers during tests.
    // @get:Rule applies the rule to this test class.
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    // This is a test method, marked with @Test.
    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() {
        // runTest is a coroutine test builder that manages test coroutines.
        runTest {
            // Create an instance of the MarsViewModel,
            // using a FakeNetworkMarsPhotosRepository instead of the real one.
            // This allows testing the ViewModel logic in isolation without actual network calls.
            val marsViewModel = MarsViewModel(
                marsPhotosRepository = FakeNetworkMarsPhotosRepository()
            )

            // Assert that the ViewModel's UI state is Success
            // and that it contains the list of photos from our fake data source.
            assertEquals(
                MarsUiState.Success(FakeDataSource.photosList),
                marsViewModel.marsUiState
            )
        }
    }
}