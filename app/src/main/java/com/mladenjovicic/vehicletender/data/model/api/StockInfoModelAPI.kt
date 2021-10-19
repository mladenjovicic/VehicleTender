package com.mladenjovicic.vehicletender.data.model.api

import android.text.BoringLayout
import com.google.gson.annotations.SerializedName

data class StockInfoModelAPI(@SerializedName("Id") var id : Int?,
                             @SerializedName("year") var year : Int?,
                             @SerializedName("modelLineId") var modelLineId:Int?,
                             @SerializedName("Mileage") var mileage : Double?,
                             @SerializedName("Price") var price : Double?,
                             @SerializedName("Comments") var comments:String?,
                             @SerializedName("Location") var locationId:Int?,
                             @SerializedName("RegNo") var regNo:String?,
                             @SerializedName("IsSold") var isSold:Boolean?
)
