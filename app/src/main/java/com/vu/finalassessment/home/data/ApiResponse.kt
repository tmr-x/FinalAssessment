package com.vu.finalassessment.home.data

//Data class to model the API response
data class ApiResponse(
    //A list of 'ResponseItem' that represents the entities in the API response.
    val entities: List<ResponseItem>,

    //Integer to represent the total number of entities in the API response.
    val entityTotal: Int
)
