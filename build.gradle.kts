// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${libs.versions.gradleTool.get()}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${libs.versions.kotlin.get()}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${libs.versions.hilt.get()}")
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
