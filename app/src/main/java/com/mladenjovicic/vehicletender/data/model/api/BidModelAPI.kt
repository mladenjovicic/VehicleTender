package com.mladenjovicic.vehicletender.data.model.api

import android.text.BoringLayout
import com.google.gson.annotations.SerializedName

data class BidModelAPI(@SerializedName("Id") var ID: Int?,
                       @SerializedName("TenderUserId") var TUserId:Int?,
                       @SerializedName("TenderStockId") var TStockId: Int,
                       @SerializedName("Price") var Price: Double,
                       @SerializedName("IsWinningPrice") var IsWinningPrice:Boolean?,
                       @SerializedName("isActive") var isActive:Boolean?) {

}