package com.mladenjovicic.vehicletender.data.model.api

import com.google.gson.annotations.SerializedName

data class LocationModelAPI(
        @SerializedName("Id") var Id : Int?,
        @SerializedName("City") var City:String?,
        @SerializedName("ZipCode") var ZipCode:String?
)
