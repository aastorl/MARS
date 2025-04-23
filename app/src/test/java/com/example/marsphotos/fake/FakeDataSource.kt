package com.example.marsphotos.fake

import com.example.marsphotos.network.MarsPhoto

// This object provides fake data to be used for testing purposes.
// It simulates having data without making actual network requests.
object FakeDataSource {
    // Define some fake IDs and image URLs.
    const val idOne = "img1"
    const val idTwo = "img2"
    const val imgOne = "url.1"
    const val imgTwo = "url.2"

    // Create a list of fake MarsPhoto objects using the defined IDs and URLs.
    val photosList = listOf(
        MarsPhoto(
            id = idOne,
            imgSrc = imgOne
        ),
        MarsPhoto(
            id = idTwo,
            imgSrc = imgTwo
        )
    )
}