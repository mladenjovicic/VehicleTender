package com.mladenjovicic.vehicletender.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "car_models", indices = arrayOf(Index(value = arrayOf("IdServer"), unique = true)))
data class CarModelDB(
        @ColumnInfo(name = "IdServer")
        var IdServer:Int?=null,
        @ColumnInfo(name ="model_name")
        var model_name:String,
        @ColumnInfo(name ="model_no")
        var model_no:String,
        @ColumnInfo(name ="manufacturer_id")
        var manufacturer_id:Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id:Int?=null
}