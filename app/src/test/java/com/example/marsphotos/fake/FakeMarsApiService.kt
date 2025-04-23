package com.example.marsphotos.fake

import com.example.marsphotos.network.MarsApiService
import com.example.marsphotos.network.MarsPhoto

// This class is a fake version of MarsApiService used for testing.
// It implements the MarsApiService interface but doesn't make real network calls.
class FakeMarsApiService : MarsApiService {
    // This overrides the getPhotos method from the interface.
    override suspend fun getPhotos(): List<MarsPhoto> {
        // Instead of fetching data from the network, it just returns the fake list of photos
        // from the FakeDataSource object.
        return FakeDataSource.photosList
    }
}