package com.mladenjovicic.vehicletender.model.db

import androidx.room.ColumnInfo

data class userModelDB1(

                    @ColumnInfo(name = "id")
                        var Id:Int,
                   @ColumnInfo(name ="contact_name")
                   var contact_name:String,
                   @ColumnInfo(name ="contact_surname")
                   var contact_surname:String,
                   @ColumnInfo(name ="email")
                   var email:String,
                   @ColumnInfo(name = "password")
                   var password:String,
                   @ColumnInfo(name ="status_user")
                   var status_user:Int,
                   @ColumnInfo(name ="id_location")
                   var id_location:String,
                   @ColumnInfo(name ="phone")
                   var phone:String,
                   @ColumnInfo(name ="company_name")
                   var company_name:String) {
}