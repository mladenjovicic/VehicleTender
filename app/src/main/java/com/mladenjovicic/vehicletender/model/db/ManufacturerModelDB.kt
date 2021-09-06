package com.mladenjovicic.vehicletender.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "manufacturer")
data class ManufacturerModelDB(
        @ColumnInfo(name ="manufacturer_name")
        var manufacturer_name:String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id:Int?=null
}