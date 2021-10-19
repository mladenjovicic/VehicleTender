package com.mladenjovicic.vehicletender.data.model.db

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class tenderWiningList(
    /* @ColumnInfo(name ="id")
     var id:Int,
     @ColumnInfo(name ="serverId")
     var serverId:Int,
     @ColumnInfo(name ="userId")
     var userId:Int,
     @ColumnInfo(name ="stockId")
     var stockId:Int,*/
    @ColumnInfo(name ="price")
    var price:Double,
    @ColumnInfo(name ="isWinningPrice")
    var isWinningPrice:Boolean,
    @ColumnInfo(name ="isActive")
    var isActive:Boolean,
    /*@ColumnInfo(name ="tenderUser")
    var tenderUser:String,*/
    @ColumnInfo(name ="email")
    var email:String,
    @ColumnInfo(name ="company_name")
    var company_name:String,
    @ColumnInfo(name ="contact_surname")
    var contact_surname:String,
    @ColumnInfo(name ="contact_name")
    var contact_name:String,
    @ColumnInfo(name ="phone")
    var phone:String
) {

 /*   @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int?=null*/
}