package com.vu.finalassessment.home.network

import com.vu.finalassessment.home.data.ApiResponse
import retrofit2.http.GET

interface RestfulApiDevService {

    @GET("/dashboard/languages") // Corrected endpoint path if necessary
    suspend fun getAllObjects(): ApiResponse // Now expects ApiResponse that contains 'entities'
}