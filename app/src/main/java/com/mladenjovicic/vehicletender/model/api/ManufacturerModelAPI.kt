package com.mladenjovicic.vehicletender.model.api

import com.google.gson.annotations.SerializedName

data class ManufacturerModelAPI(@SerializedName("ID") var ID : Int?,
                                @SerializedName("ManufacturerName") var city:String?)
