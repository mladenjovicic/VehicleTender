package com.mladenjovicic.vehicletender.data.model.api

import com.google.gson.annotations.SerializedName

data class TenderUserModelAPI(@SerializedName("id") var id : Int?,
                              @SerializedName("tenderId") var tenderId : Int?,
                              @SerializedName("userId") var userId:String?) {
}