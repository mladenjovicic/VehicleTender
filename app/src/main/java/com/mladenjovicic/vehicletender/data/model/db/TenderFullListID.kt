package com.mladenjovicic.vehicletender.data.model.db

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class TenderFullListID(@ColumnInfo(name ="id")
                            var id:Int,
                            @ColumnInfo(name ="stockId")
                            var stockId:Int,
                            @ColumnInfo(name ="tenderId")
                            var tenderId:String,
                            @ColumnInfo(name ="saleDate")
                            var saleDate:String,
                            @ColumnInfo(name ="stockInfoId")
                            var stockInfoId:Int,
                            @ColumnInfo(name ="year")
                            var year:Int,
                            @ColumnInfo(name ="mileage")
                            var mileage:Double,
                            @ColumnInfo(name ="price")
                            var price:Double,
                            @ColumnInfo(name ="comments")
                            var comments:String,
                            @ColumnInfo(name ="regNo")
                            var regNo:String,
                            @ColumnInfo(name ="isSold")
                            var isSold:Boolean,
                            @ColumnInfo(name ="city")
                            var city:String,
                            @ColumnInfo(name ="zipCOde")
                            var zipCOde:String,
                            @ColumnInfo(name ="model_name")
                            var model_name:String,
                            @ColumnInfo(name ="model_no")
                            var model_no:String,
                            @ColumnInfo(name ="manufacturer_name")
                            var manufacturer_name:String) {
    /*@PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tenderStockId")
    var Id:Int?=null*/
}