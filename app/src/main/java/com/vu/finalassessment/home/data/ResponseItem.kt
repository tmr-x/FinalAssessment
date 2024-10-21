package com.vu.finalassessment.home.data

//Import necessary libraries
import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

//Allows this object to be serialized and passed between components.
@Parcelize

//Maps the various JSON fields for the information received from the API.
data class ResponseItem(
    @Json(name = "name") val name: String,
    @Json(name = "family") val family: String,
    @Json(name = "branch") val branch: String,
    @Json(name = "speakers") val speakers: Int,
    @Json(name = "writingSystem") val writingSystem: String,
    @Json(name = "officialIn") val officialIn: List<String>,
    @Json(name = "description") val description: String
) : Parcelable  //Implements Parcelable.