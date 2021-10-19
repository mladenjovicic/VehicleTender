package com.mladenjovicic.vehicletender.data.model.api

import com.google.gson.annotations.SerializedName

data class TenderUserModelAPI(
        @SerializedName("Id") var id : Int?,
        @SerializedName("TenderId") var tenderId : Int?,
        @SerializedName("UserId") var userId:String?) {
}