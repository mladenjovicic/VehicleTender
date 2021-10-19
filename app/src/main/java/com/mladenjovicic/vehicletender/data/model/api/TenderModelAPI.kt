package com.mladenjovicic.vehicletender.data.model.api

import com.google.gson.annotations.SerializedName

data class TenderModelAPI(@SerializedName("Id") var id : Int?,
                          @SerializedName("CreatedDate") var createdDate : String?,
                          @SerializedName("UserId") var createdBy:String?,
                          @SerializedName("TenderNo") var tenderNo : String?,
                          @SerializedName("OpenDate") var openDate : String?,
                          @SerializedName("CloseDate") var closeDate:String?,
                          @SerializedName("StatusId") var statusId:Int?)
