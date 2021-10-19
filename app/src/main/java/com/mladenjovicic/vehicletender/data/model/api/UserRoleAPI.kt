package com.mladenjovicic.vehicletender.data.model.api

import com.google.gson.annotations.SerializedName

data class UserRoleAPI(
             @SerializedName("Id") var ID : String,
             @SerializedName("Name") var Name:String
                       ) {
}