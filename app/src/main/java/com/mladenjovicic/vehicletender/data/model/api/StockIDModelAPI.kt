package com.mladenjovicic.vehicletender.data.model.api

import com.google.gson.annotations.SerializedName

data class StockIDModelAPI(
    @SerializedName("Id") var id : Int,
    @SerializedName("IsSold") var IsSold : Boolean) {
}