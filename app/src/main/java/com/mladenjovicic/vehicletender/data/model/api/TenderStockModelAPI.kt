package com.mladenjovicic.vehicletender.data.model.api

import com.google.gson.annotations.SerializedName

data class TenderStockModelAPI(@SerializedName("id") var id : Int?,
                               @SerializedName("stockId") var stockId:Int?,
                               @SerializedName("tenderId") var tenderId : String?,
                               @SerializedName("saleDate") var saleDate:String?) {
}