package com.vu.finalassessment.home.network

//Import necessary libraries
import com.vu.finalassessment.home.data.ApiResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

//Define an interface for the RESTful API service.
interface RestfulApiDevService {

    /*Uses a POST request to the relevant endpoint for the user login.
    * Returns a response object that contains a string.*/
    @POST("/footscray/auth")
    suspend fun login(@Body requestBody: RequestBody): Response<String>

    //Uses a GET request to fetch all language objects from the relevant endpoint.
    @GET("/dashboard/languages")
    suspend fun getAllObjects(): ApiResponse // Expects an ApiResponse object containing 'entities'.
}