plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.bocc.recite"
    compileSdk = libs.versions.compileSdk.get().toInt()
    
    defaultConfig {
        applicationId = "com.bocc.recite"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro",
                "retrofit2.pro"
            )
        }
    }
    
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    
    // AndroidX Core
    implementation(libs.bundles.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.material)
    
    // Kotlin
    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.bundles.kotlinx.coroutines)
    
    // Lifecycle
    implementation(libs.bundles.androidx.lifecycle)
    
    // Networking
    implementation(libs.bundles.retrofit)
    
    // Reactive
    implementation(libs.rxandroid)
    
    // Room
    implementation(libs.bundles.room)
    kapt(libs.androidx.room.compiler)
    
    // Dependency Injection
    implementation(libs.bundles.hilt)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.compiler)
    
    // Image Loading
    implementation(libs.bundles.glide)
    kapt(libs.glide.compiler)
    
    // Other
    implementation(libs.rebound)
    
    // Testing
    testImplementation(libs.bundles.testing)
}
