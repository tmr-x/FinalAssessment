package com.vu.finalassessment

//Import necessary libraries.
import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyBaseApplication: Application() {
    //Called when the application is initially starting.
    override fun onCreate() {
        super.onCreate()

        //Prints a message in LogCat that indicates the Application's onCreate method has been invoked.
        Log.d("nit3213", "Application onCreate was called")
    }
}
