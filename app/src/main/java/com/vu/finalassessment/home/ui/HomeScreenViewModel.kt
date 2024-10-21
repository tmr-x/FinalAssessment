package com.vu.finalassessment.home.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vu.finalassessment.home.data.ApiResponse
import com.vu.finalassessment.home.data.ResponseItem
import com.vu.finalassessment.home.network.RestfulApiDevRetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val _greetingText = MutableStateFlow("Final Assessment")
    val greetingText = _greetingText.asStateFlow()  // Expose as immutable

    private val _apiResponseObjects = MutableStateFlow<List<ResponseItem>>(listOf())
    val apiResponseObjects = _apiResponseObjects.asStateFlow()  // Expose as immutable

    private val restfulDevApiService = RestfulApiDevRetrofitClient().apiService

    init {
        Log.d("nit3213", "HomeScreenViewModel ViewModel injected")

        fetchApiResponse()
    }

    private fun fetchApiResponse() {
        viewModelScope.launch {
            try {
                val apiResponse: ApiResponse = restfulDevApiService.getAllObjects()
                _apiResponseObjects.value = apiResponse.entities // Extract the 'entities' field
            } catch (e: Exception) {
                Log.e("API Error", "Failed to fetch data: ${e.message}")
                _apiResponseObjects.value = emptyList()
            }
        }
    }
}