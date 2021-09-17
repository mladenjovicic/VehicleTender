package com.mladenjovicic.vehicletender.data.model.api

import com.google.gson.annotations.SerializedName

data class TenderModelAPI(@SerializedName("id") var id : Int?,
                          @SerializedName("createdDate") var createdDate : String?,
                          @SerializedName("createdBy") var createdBy:String?,
                          @SerializedName("tenderNo") var tenderNo : String?,
                          @SerializedName("openDate") var openDate : String?,
                          @SerializedName("closeDate") var closeDate:String?,
                          @SerializedName("statusId") var statusId:Int?)
