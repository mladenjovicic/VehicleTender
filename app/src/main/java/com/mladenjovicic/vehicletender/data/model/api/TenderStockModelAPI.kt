package com.mladenjovicic.vehicletender.data.model.api

import com.google.gson.annotations.SerializedName

data class TenderStockModelAPI(@SerializedName("Id") var id : Int?,
                               @SerializedName("StockId") var stockId:Int?,
                               @SerializedName("TenderId") var tenderId : Int?,
                               @SerializedName("SaleDate") var saleDate:String?,
                               @SerializedName("isDeleted") var isDeleted:Boolean?) {
}