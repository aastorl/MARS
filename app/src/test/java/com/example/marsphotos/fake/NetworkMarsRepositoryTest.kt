package com.example.marsphotos.fake

import com.example.marsphotos.data.NetworkMarsPhotosRepository
import com.example.marsphotos.fake.FakeDataSource // Assuming FakeDataSource is in this package
import com.example.marsphotos.fake.FakeMarsApiService // Assuming FakeMarsApiService is in this package
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

// This is a test class for the NetworkMarsPhotosRepository.
class NetworkMarsRepositoryTest {

    // This is a test method, marked with @Test.
    // It tests the getMarsPhotos method of the repository.
    @Test
    fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList(): Unit =
        // runTest is used to run suspend functions in tests.
        runTest {
            // Create an instance of the NetworkMarsPhotosRepository,
            // injecting a FakeMarsApiService. This way, the test uses fake data
            // instead of making a real network call.
            val repository = NetworkMarsPhotosRepository(
                marsApiService = FakeMarsApiService()
            )

            // Assert that the list of photos returned by the repository
            // matches the fake list from FakeDataSource.
            assertEquals(FakeDataSource.photosList, repository.getMarsPhotos())
        }
    // This test checks if the getMarsPhotos method of NetworkMarsPhotosRepository
    // returns the correct list of photos when given a simulated FakeMarsApiService.
}