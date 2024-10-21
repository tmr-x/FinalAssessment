package com.example.finalassessment.home.network

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @POST("/footscray/auth")
    suspend fun login(@Body requestBody: RequestBody): Response<String>

    @GET("/dashboard/languages")
    suspend fun getLanguages(@Header("keypass") keypass: String): Response<String>
}