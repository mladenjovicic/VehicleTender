package com.mladenjovicic.vehicletender.data.model.api

import com.google.gson.annotations.SerializedName

data class BidModelAPI(@SerializedName("id") var ID: Int?,
                       @SerializedName("TUserId") var TUserId:String?,
                       @SerializedName("TStockId") var TStockId: Int,
                       @SerializedName("Price") var Price: Double,
                       @SerializedName("IsWinningPrice") var IsWinningPrice:Boolean?) {

}