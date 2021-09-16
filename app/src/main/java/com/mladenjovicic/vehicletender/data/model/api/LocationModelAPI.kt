package com.mladenjovicic.vehicletender.data.model.api

import com.google.gson.annotations.SerializedName

data class LocationModelAPI(
        @SerializedName("id") var id : Int?,
        @SerializedName("City") var city:String?,
        @SerializedName("ZipCode") var zipCOde:String?
)
