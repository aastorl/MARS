package com.example.marsphotos.fake

import com.example.marsphotos.data.MarsPhotosRepository
import com.example.marsphotos.fake.FakeDataSource // Assuming FakeDataSource is in this package
import com.example.marsphotos.network.MarsPhoto

// This class is a fake version of MarsPhotosRepository used for testing.
// It implements the MarsPhotosRepository interface but doesn't rely on real network calls.
class FakeNetworkMarsPhotosRepository : MarsPhotosRepository {
    // This overrides the getMarsPhotos method from the interface.
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        // Instead of fetching data from a real service, it just returns the fake list of photos
        // directly from the FakeDataSource object.
        return FakeDataSource.photosList
        // Returning the fake data is the key way we simulate the real repository's behavior for tests.
    }
}