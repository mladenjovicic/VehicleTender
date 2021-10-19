package com.mladenjovicic.vehicletender.data.model.api

import android.text.BoringLayout
import com.google.gson.annotations.SerializedName

data class StockInfoModelAPI(@SerializedName("Id") var id : Int?,
                             @SerializedName("Year") var year : Int?,
                             @SerializedName("ModelLineId") var modelLineId:Int?,
                             @SerializedName("Mileage") var mileage : Int?,
                             @SerializedName("Price") var price : Double?,
                             @SerializedName("Comments") var comments:String?,
                             @SerializedName("LocationId") var locationId:Int?,
                             @SerializedName("RegNo") var regNo:String?,
                             @SerializedName("IsSold") var isSold:Boolean?
)
