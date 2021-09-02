package com.mladenjovicic.vehicletender.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LocationModel")
data class LocationModelDB(
    @ColumnInfo(name ="city")
    var city:String,
    @ColumnInfo(name = "zipCode")
    var zipCode:String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id:Int?=null
}