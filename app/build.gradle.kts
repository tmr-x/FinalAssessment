plugins {
    alias(libs.plugins.android.application) //Android application plugin
    alias(libs.plugins.jetbrains.kotlin.android) //Kotlin android plugin
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

//Defines the namespace, SDK version(s), unique identifiers and other features of the application
android {
    namespace = "com.vu.finalassessment"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.vu.finalassessment"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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

    //JVM target version for Kotlin
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    //Core Android libraries and utilities
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.gson)
    implementation(libs.retrofit2.converter.gson)


    //AndroidX libraries
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //Navigation component
    implementation(libs.androidx.navigation.fragment)

    //Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.fragment.testing)
    androidTestImplementation(libs.androidx.core)
    androidTestImplementation(libs.androidx.junit.v115)
    androidTestImplementation(libs.androidx.espresso.core.v351)
    //JUnit
    testImplementation(libs.junit)
    //MockK
    testImplementation(libs.mockk)
    //Android instrumentation testing
    androidTestImplementation(libs.androidx.junit.v113)
    androidTestImplementation(libs.androidx.espresso.core.v340)

    //Retrofit dependencies
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.logging.interceptor)
    implementation(libs.retrofit2.converter.scalars)

    //Glide for image loading
    implementation(libs.glide)

    //Kotlin Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)

    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler.v2511)
}

kapt {
    correctErrorTypes = true
}