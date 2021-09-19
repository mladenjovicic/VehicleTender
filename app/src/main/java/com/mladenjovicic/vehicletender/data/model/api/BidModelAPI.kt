package com.mladenjovicic.vehicletender.data.model.api

import com.google.gson.annotations.SerializedName

data class BidModelAPI(@SerializedName("id") var ID : Int?,
                       @SerializedName("TUserId") var TUserId:String?,
                       @SerializedName("TStockId") var TStockId:String?,
                       @SerializedName("Price") var Price:String?,
                       @SerializedName("IsWinningPrice") var IsWinningPrice:Boolean?) {

}