package com.vu.finalassessment.home.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseItem(
    @Json(name = "name") val name: String,
    @Json(name = "family") val family: String,
    @Json(name = "branch") val branch: String,
    @Json(name = "speakers") val speakers: Int,
    @Json(name = "writingSystem") val writingSystem: String,
    @Json(name = "officialIn") val officialIn: List<String>,
    @Json(name = "description") val description: String
) : Parcelable