package com.mladenjovicic.vehicletender.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "tender")
data class TenderModelDB(
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
