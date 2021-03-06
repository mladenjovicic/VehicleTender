package com.mladenjovicic.vehicletender.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "tender", indices = arrayOf(Index(value = arrayOf("tenderNo"), unique = true)))
data class TenderModelDB(
    @ColumnInfo(name = "idServer")
    var idServer:Int?,
    @ColumnInfo(name = "createdDate")
    var createdDate:String,
    @ColumnInfo(name = "createdBy")
    var createdBy:String,
    @ColumnInfo(name = "tenderNo")
    var tenderNo:String,
    @ColumnInfo(name = "openDate")
    var openDate:String,
    @ColumnInfo(name = "closeDate")
    var closeDate:String,
    @ColumnInfo(name = "statusId")
    var statusId:Int
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int?=null
}
