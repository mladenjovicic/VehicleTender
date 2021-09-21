package com.mladenjovicic.vehicletender.data.model.api

import com.google.gson.annotations.SerializedName

data class TenderUserModelAPI(@SerializedName("id") var id : Int?,
                              @SerializedName("tenderId") var tenderId : String?,
                              @SerializedName("userId") var userId:String?) {
}