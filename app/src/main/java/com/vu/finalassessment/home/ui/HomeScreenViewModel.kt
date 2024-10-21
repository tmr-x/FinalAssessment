package com.vu.finalassessment.home.ui

//Import necessary libraries.
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vu.finalassessment.home.data.ApiResponse
import com.vu.finalassessment.home.data.ResponseItem
import com.vu.finalassessment.home.network.RestfulApiDevService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor( //Inject ApiService using Hilt
    private val restfulDevApiService: RestfulApiDevService //Instance of the API service injected
) : ViewModel() {

    //Holds the greeting text set to "Final Assessment".
    //Mutable so that the text may be changed when necessary.
    private val _greetingText = MutableStateFlow("Final Assessment")
    val greetingText = _greetingText.asStateFlow()

    //Hold a list of ResponseItem objects, starting as an empty list.
    //Mutable to allow the list to change over time if needed.
    private val _apiResponseObjects = MutableStateFlow<List<ResponseItem>>(listOf())
    val apiResponseObjects = _apiResponseObjects.asStateFlow()

    //Initialization block for the ViewModel.
    init {
        Log.d("nit3213", "HomeScreenViewModel ViewModel injected")
        fetchApiResponse()
    }

    //Function to fetch data from the API.
    private fun fetchApiResponse() {
        viewModelScope.launch {
            try {
                val apiResponse: ApiResponse = restfulDevApiService.getAllObjects() //Use the injected API service.
                _apiResponseObjects.value = apiResponse.entities
            } catch (e: Exception) {
                Log.e("API Error", "Failed to fetch data: ${e.message}")
                _apiResponseObjects.value = emptyList()
            }
        }
    }
}