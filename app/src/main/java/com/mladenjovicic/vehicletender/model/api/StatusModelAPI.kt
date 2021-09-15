package com.mladenjovicic.vehicletender.model.api

import com.google.gson.annotations.SerializedName

data class StatusModelAPI(@SerializedName("id") var id : Int?,
                          @SerializedName("type") var city:String?
) {
}