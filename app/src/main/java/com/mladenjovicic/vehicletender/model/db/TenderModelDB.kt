package com.mladenjovicic.vehicletender.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tenderModel")
data class TenderModelDB(
    @ColumnInfo(name = "colona1")
    var colona1:String,
    @ColumnInfo(name = "colona2")
    var colona2:String
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id:Int?=null
}
