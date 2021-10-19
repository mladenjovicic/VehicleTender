package com.mladenjovicic.vehicletender.data.model.api

import android.text.BoringLayout
import com.google.gson.annotations.SerializedName

data class UserProfilAPI(@SerializedName("Id") var ID : String?,
                         @SerializedName("Email") var Email:String?,
                         @SerializedName("UserName") var UserName : String?,
                         @SerializedName("LocationId") var LocationId:Int?,
                         @SerializedName("isActive") var isActive : Boolean?,
                         @SerializedName("FirstName") var FirstName:String?,
                         @SerializedName("LastName") var LastName : String?,
                         @SerializedName("PhoneNumber") var PhoneNumber:String?,
                         @SerializedName("RoleName") var RoleName : String?,
                         @SerializedName("RoleId") var RoleId:String?,
                         @SerializedName("CompanyName") var CompanyName : String?){
}