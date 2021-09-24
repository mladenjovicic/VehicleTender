package com.mladenjovicic.vehicletender.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "manufacturer", indices = arrayOf(Index(value = arrayOf("IdServer"), unique = true)))
data class ManufacturerModelDB(
        @ColumnInfo(name = "IdServer")
        var IdServer:Int?=null,
        @ColumnInfo(name ="manufacturer_name")
        var manufacturer_name:String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id:Int?=null
}