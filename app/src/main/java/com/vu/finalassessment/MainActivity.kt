package com.vu.finalassessment

//Import necessary libraries.
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //Called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("nit3213", "onCreate: ")
        enableEdgeToEdge() //Enable edge-to-edge layout.

        setContentView(R.layout.activity_main)  //Set the content view to the main activity layout.

        // Setup navigation to the HomeScreenFragment after login is successful
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.homeContainer) as NavHostFragment
        val navController = navHostFragment.navController

        //Navigate to HomeScreenFragment directly upon creation
        navController.navigate(R.id.homeScreenFragment)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            // Set padding for the view.
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    //Called when the activity becomes visible to the user.
    override fun onStart() {
        super.onStart()
        Log.d("nit3213", "onStart: ")
    }

    //Called when the activity starts interacting with the user.
    override fun onResume() {
        super.onResume()
        Log.d("nit3213", "onResume: ")
    }

    //.Called when the activity is about to go into the background
    override fun onPause() {
        super.onPause()
        Log.d("nit3213", "onPause: ")
    }

    //Called when the activity is no longer visible.
    override fun onStop() {
        super.onStop()
        Log.d("nit3213", "onStop: ")
    }

    //Called when the activity is destroyed.
    override fun onDestroy() {
        super.onDestroy()
        Log.d("nit3213", "onDestroy: ")
    }
}