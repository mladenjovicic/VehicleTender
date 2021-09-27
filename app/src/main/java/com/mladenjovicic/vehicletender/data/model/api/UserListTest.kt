package com.mladenjovicic.vehicletender.data.model.api

import com.google.gson.annotations.SerializedName

data class UserListTest(
        @SerializedName("Id") var Id: String?,
        @SerializedName("Email") var Email:String?,
        @SerializedName("UserName") var UserName: Int?,
        @SerializedName("Address") var Address: String?,
        @SerializedName("City") var City: String,
        @SerializedName("Country") var Country: String?,
        @SerializedName("isActive") var isActive:String?,
        @SerializedName("FirstName") var FirstName: String?,
        @SerializedName("PhoneNumber") var PhoneNumber: String?,
        @SerializedName("LastName") var LastName: String?,
        @SerializedName("RoleName") var RoleName: String?,
        @SerializedName("RoleId") var RoleId:String?,
        @SerializedName("DealerName") var DealerName: String?,
        @SerializedName("CreatedBy") var CreatedBy: String?,
        @SerializedName("FullName") var FullName: String?
) {
}