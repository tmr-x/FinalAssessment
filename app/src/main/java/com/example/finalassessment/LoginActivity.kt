package com.example.finalassessment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalassessment.databinding.ActivityLoginBinding
import com.example.finalassessment.home.network.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.converter.scalars.ScalarsConverterFactory
import org.json.JSONObject
import retrofit2.Retrofit

class LoginActivity : AppCompatActivity() {

    // Preset username and password for assessment
    private val validUsername = "taylah"
    private val validPassword = "s4673381"
    private val hardcodedKeypass = "languages" // Changed to 'languages'

    private lateinit var binding: ActivityLoginBinding
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Retrofit for API calls
        val retrofit = Retrofit.Builder()
            .baseUrl("https://nit3213-api-h2b3-latest.onrender.com")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        // Set up login button click listener
        binding.enterLogin.setOnClickListener {
            val username = binding.usernameInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()

            // Check if entered username and password match the preset values
            if (username == validUsername && password == validPassword) {
                login(username, password)
            } else {
                showToast("Invalid username or password")
            }
        }
    }

    // Function to handle login logic
    private fun login(username: String, password: String) {
        val loginRequestJson = """
        {
            "username": "$username",
            "password": "$password"
        }
        """.trimIndent()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val mediaType = MediaType.parse("application/json")
                val requestBody = RequestBody.create(mediaType, loginRequestJson)

                val response = apiService.login(requestBody)

                if (response.isSuccessful) {
                    val responseBody = response.body() ?: ""
                    val jsonResponse = JSONObject(responseBody)

                    // Log the entire response for debugging
                    Log.d("LoginActivity", "API Response: $jsonResponse")

                    // Check if "keypass" is part of the response
                    if (jsonResponse.has("keypass")) {
                        val keypass = jsonResponse.getString("keypass")

                        // Log the keypass value for debugging
                        Log.d("LoginActivity", "Keypass received: $keypass")

                        if (keypass == hardcodedKeypass) { // Check for the expected hardcoded keypass
                            withContext(Dispatchers.Main) {
                                val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                                intent.putExtra("keypass", keypass)
                                startActivity(intent)
                                finish() // Close the LoginActivity
                            }
                        } else {
                            withContext(Dispatchers.Main) {
                                showToast("Invalid keypass: $keypass")
                            }
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            showToast("Error: No keypass in response")
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        showToast("Login failed: ${response.errorBody()?.string() ?: "Unknown error"}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showToast("Error: ${e.message}")
                }
            }
        }
    }

    // Function to display toast messages
    private fun showToast(message: String) {
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
        }
    }
}