package com.mladenjovicic.vehicletender.data.model.api

import com.google.gson.annotations.SerializedName

data class ManufacturerModelAPI(@SerializedName("Id") var ID : Int?,
                                @SerializedName("ManufacturerName") var ManufacturerName:String?)
