plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
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
        compose = true
        viewBinding = true
    }
    
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    
    // Compose BOM
    implementation(platform(libs.androidx.compose.bom))
    
    // AndroidX Core
    implementation(libs.bundles.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.material)
    
    // Compose
    implementation(libs.bundles.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation("androidx.lifecycle:lifecycle-runtime-compose:${libs.versions.lifecycle.get()}")
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    
    // Kotlin
    implementation(libs.bundles.kotlinx.coroutines)
    
    // Lifecycle
    implementation(libs.bundles.androidx.lifecycle)
    
    // Networking
    implementation(libs.bundles.retrofit)
    
    // Reactive
    implementation(libs.rxandroid)
    
    // Room
    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)
    
    // Dependency Injection
    implementation(libs.bundles.hilt)
    ksp(libs.hilt.android.compiler)
    ksp(libs.hilt.compiler)
    
    // Image Loading
    implementation(libs.bundles.glide)
    ksp(libs.glide.compiler)
    implementation(libs.coil.compose)
    
    // Other
    implementation(libs.rebound)
    
    // Testing
    testImplementation(libs.bundles.testing)
    androidTestImplementation(libs.bundles.compose.testing)
    debugImplementation(libs.androidx.compose.ui.tooling)
}
