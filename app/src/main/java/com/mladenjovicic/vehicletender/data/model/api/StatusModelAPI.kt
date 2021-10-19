package com.mladenjovicic.vehicletender.data.model.api

import com.google.gson.annotations.SerializedName

data class StatusModelAPI(@SerializedName("Id") var id : Int?,
                          @SerializedName("Type") var type:String?
) {
}