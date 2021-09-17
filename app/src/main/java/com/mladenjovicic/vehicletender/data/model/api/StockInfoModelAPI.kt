package com.mladenjovicic.vehicletender.data.model.api

import android.text.BoringLayout
import com.google.gson.annotations.SerializedName

data class StockInfoModelAPI(@SerializedName("id") var id : Int?,
                             @SerializedName("year") var year : Int?,
                             @SerializedName("modelLineId") var modelLineId:Int?,
                             @SerializedName("mileage") var mileage : Double?,
                             @SerializedName("price") var price : Double?,
                             @SerializedName("comments") var comments:String?,
                             @SerializedName("locationId") var locationId:Int?,
                             @SerializedName("regNo") var regNo:String?,
                             @SerializedName("isSold") var isSold:Boolean?
)
