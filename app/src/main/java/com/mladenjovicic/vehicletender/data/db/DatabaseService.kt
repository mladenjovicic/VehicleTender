package com.mladenjovicic.vehicletender.data.db

import android.content.Context
import androidx.room.Room

class DatabaseService private constructor(private val applicationContext: Context){
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            VTDatabase::class.java,
            "vt_database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    val locationDAO: DAOAcessLocation
        get() = database.locationDAO()

    val userDAO: DAOAcessUser
        get() = database.userDAO()

    val manufacturerDAO: DAOAcessManafactura
        get() = database.manufacturerDAO()

    val carModelDAO: DAOAcessCarModels
        get() = database.carModelDAO()

    val bidDAO: DAOAcessBid
        get() = database.bidDAO()

    val statusDAO: DAOAcessStatus
        get() = database.statusDAO()

    val stockInfoDAO: DAOAcessStockInfo
        get() = database.stockInfoDAO()

    val tenderDAO: DAOAcessTender
        get() = database.tenderDAO()

    val tenderStockDAO: DAOAcessTenderStock
        get() = database.tenderStockDAO()

    val tenderUserDAO: DAOAcessTenderUser
        get() = database.tenderUserDAO()

    companion object {
        @Volatile
        private var instance: DatabaseService? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: DatabaseService(context).also { instance = it }
        }
}}