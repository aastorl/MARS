package com.example.marsphotos.data

import com.example.marsphotos.network.MarsApi // Might not be used in the end, but kept in case it's relevant
import com.example.marsphotos.network.MarsApiService
import com.example.marsphotos.network.MarsPhoto

/**
 * Defines what a repository should do to get Mars photos.
 * It's like a contract: any class implementing this interface knows how to get photos.
 * This helps separate the logic (how photos are obtained) from where they are used.
 */
interface MarsPhotosRepository {
    /**
     * Gets the list of Mars photos asynchronously (without blocking the program).
     */
    suspend fun getMarsPhotos() : List <MarsPhoto>
}
/**
 * Repository implementation that gets Mars photos from the network.
 * It uses an API service that is passed to it to make the network calls.
 *
 * @param marsApiService The service that actually knows how to talk to the Mars API.
 * By passing this service (Dependency Injection), we make this class more flexible and testable.
 */
class NetworkMarsPhotosRepository(
    private val marsApiService: MarsApiService // We receive the API service here (Dependency Injection)
) : MarsPhotosRepository {

    /**
     * Gets Mars photos by asking the API service it received.
     * This fulfills the contract of the [MarsPhotosRepository] interface.
     */
    override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()
}

// Benefits of the new approach (Dependency Injection):
// 1. Less Coupling: The repository isn't tied to a specific way of creating the API service.
// 2. Easier Testing: You can give it a fake (mock) API service to test this class without real network calls.
// 3. Flexibility: If the source of photos changes (another API, database), you can use a different MarsApiService implementation
//    without changing this repository class.
// 4. Organization: Separates responsibilities - the repository USES the service, but doesn't care HOW it's created or works inside.