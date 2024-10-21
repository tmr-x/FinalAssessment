package com.vu.finalassessment.home.network

//Import necessary libraries
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

//This class is used to make network requests to the RESTful API.
class RestfulApiDevRetrofitClient {

    // Define the base URL for the API.
    private val BASE_URL = "https://nit3213-api-h2b3-latest.onrender.com"

    //Create an HTTP logging interceptor.
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    //Build an OkHttpClient.
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging) //Add the previously defined logging interceptor to OkHttpClient.
        .build()

    //Set up Moshi as the JSON converter.
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    //Build the Retrofit instance and specifying the base URL.
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(client)
        .build()

    // Create an instance of the API service.
    val apiService: RestfulApiDevService = retrofit.create(RestfulApiDevService::class.java)
}