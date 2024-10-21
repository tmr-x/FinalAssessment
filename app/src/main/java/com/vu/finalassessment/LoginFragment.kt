package com.vu.finalassessment

//Import necessary libraries.
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.vu.finalassessment.databinding.FragmentLoginBinding
import com.vu.finalassessment.home.network.RestfulApiDevService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

@AndroidEntryPoint
class LoginFragment : Fragment() {

    //Preset username and password for the assessment.
    private val validUsername = "taylah"
    private val validPassword = "s4673381"
    private val hardcodedKeypass = "languages"

    private lateinit var binding: FragmentLoginBinding
    private lateinit var apiService: RestfulApiDevService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Load GIF into the ImageView using Glide.
        val gifImageView: ImageView = view.findViewById(R.id.globeGif1)
        Glide.with(this)
            .asGif()
            .load(R.drawable.rotating_globe)
            .into(gifImageView)

        //Setup Retrofit for API calls using ScalarsConverterFactory.
        val retrofit = Retrofit.Builder()
            .baseUrl("https://nit3213-api-h2b3-latest.onrender.com")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        apiService = retrofit.create(RestfulApiDevService::class.java)

        //Set up login button click listener.
        binding.enterLogin.setOnClickListener {
            val username = binding.usernameInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()

            //Check if entered username and password match the preset values.
            if (username == validUsername && password == validPassword) {
                login(username, password)
            } else {
                showToast("Invalid username or password")
            }
        }
    }

    //Function to handle login logic.
    private fun login(username: String, password: String) {
        val loginRequestJson = """
        {
            "username": "$username",
            "password": "$password"
        }
        """.trimIndent()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val mediaType = "application/json".toMediaTypeOrNull()
                val requestBody = RequestBody.create(mediaType, loginRequestJson)

                //Make the API call using the specified login endpoint.
                val response = apiService.login(requestBody)

                if (response.isSuccessful) {
                    val responseBody = response.body() ?: ""
                    val jsonResponse = JSONObject(responseBody)

                    //Log the entire response for debugging.
                    Log.d("LoginFragment", "API Response: $jsonResponse")

                    //Check if "keypass" is part of the response.
                    if (jsonResponse.has("keypass")) {
                        val keypass = jsonResponse.getString("keypass")

                        Log.d("LoginFragment", "Keypass received: $keypass")

                        if (keypass == hardcodedKeypass) { //Check for the expected hardcoded keypass.
                            withContext(Dispatchers.Main) {
                                val intent = Intent(requireActivity(), MainActivity::class.java)
                                intent.putExtra("keypass", keypass)
                                startActivity(intent)
                                requireActivity().finish() //Close the LoginActivity.
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

    //Function to display toast messages.
    private fun showToast(message: String) {
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }
}