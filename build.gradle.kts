// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    //Specifies where the dependencies for this project will be fetched from
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Adds a classpath dependency for the Safe Args Gradle plugin
        classpath(libs.androidx.navigation.safe.args.gradle.plugin)
    }
}

plugins {
    //Alias for the Android application plugin. It is not applied at the top-level (false)
    alias(libs.plugins.android.application) apply false
    //Alias for the Kotlin plugin for Android projects. It is not applied at the top-level (false)
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    //Hilt-Android gradle-plugin
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
}