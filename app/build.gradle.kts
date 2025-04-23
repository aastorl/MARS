/*
* Copyright (C) 2023 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      https://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0-RC3"
}

android {
    namespace = "com.example.marsphotos"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.marsphotos"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Import the Compose BOM
    implementation(platform("androidx.compose:compose-bom:2024.05.00"))
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // --------------------------
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Retrofit
    // Retrofit2 es la versión actualizada de la biblioteca de Retrofit
    // --------------------------
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    // Kotlin serialization
    // Esta dependencia proporciona serialización JSON para los proyectos de Kotlin.
    // --------------------------
    // implementation("com.squareup.retrofit2:converter-scalars:2.9.0")
    // Retrofit with scalar Converter
    // --------------------------
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    // Retrofit with Kotlin serialization Converter
    // Se reemplaza el convertidor: < implementation("com.squareup.retrofit2:converter-scalars:2.9.0") >
    // porque Kotlin serialization ofrece una forma más robusta y eficiente de manejar las respuestas de la API,
    // especialmente cuando se trabaja con datos estructurados en formato JSON y se aprovecha el lenguaje Kotlin.
    // Este conversor escalar permite que Retrofit muestre el resultado JSON como String
    // kotlinx.serialization analiza estos datos JSON y los convierte en objetos Kotlin
    // --------------------------
    implementation("io.coil-kt:coil-compose:2.4.0")
    // Coil para cargar imágenes
    // --------------------------
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
    // Para pruebas de testeo
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    debugImplementation("androidx.compose.ui:ui-tooling")
}
