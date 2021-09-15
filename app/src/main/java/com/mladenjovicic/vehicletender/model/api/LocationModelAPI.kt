package com.mladenjovicic.vehicletender.model.api

import com.google.gson.annotations.SerializedName

data class LocationModelAPI(
        @SerializedName("id") var id : Int?,
        @SerializedName("city") var city:String?,
        @SerializedName("zipCOde") var zipCOde:String?
)
