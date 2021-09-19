package com.mladenjovicic.vehicletender.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
data class LocationModelDB(
    @ColumnInfo(name = "idServer")
    var idServer:Int?=null,
    @ColumnInfo(name ="city")
    var city:String,
    @ColumnInfo(name = "zipCOde")
    var zipCOde:String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id:Int?=null
}