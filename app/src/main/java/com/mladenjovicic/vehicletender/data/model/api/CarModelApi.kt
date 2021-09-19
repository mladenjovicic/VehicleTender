package com.mladenjovicic.vehicletender.data.model.api

import com.google.gson.annotations.SerializedName

data class CarModelApi(@SerializedName("id") var ID : Int?,
                       @SerializedName("ModelName") var ModelName:String?,
                       @SerializedName("ModelNO") var ModelNO:String?,
                       @SerializedName("ManufacturerId") var ManufacturerId:Int?)
