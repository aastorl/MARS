package com.example.marsphotos.network

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(InternalSerializationApi::class)
// This annotation is needed because @Serializable (or its underlying mechanisms)
// is currently considered part of an internal API in this version.
// It tells the compiler you understand you're using something that might change in the future.
@Serializable
// This annotation from kotlinx.serialization means this class can be converted to/from JSON.
data class MarsPhoto (
    val id: String,
    // The 'id' property in the JSON will be mapped directly to this 'id' property.

    @SerialName(value = "img_src")
    // This annotation tells the serializer to use the JSON key "img_src"
    // for the property below, even though the property name in Kotlin is different.
    val imgSrc: String
    // We use 'imgSrc' in Kotlin for better readability and to avoid special characters like underscores.
)
// This data class represents a single Mars photo object received from the web service.
// Each property here corresponds to a key in the JSON object for a photo.