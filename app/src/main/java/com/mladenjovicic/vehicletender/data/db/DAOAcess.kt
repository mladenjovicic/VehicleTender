package com.mladenjovicic.vehicletender.data.db


import androidx.lifecycle.LiveData
import androidx.room.*
import com.mladenjovicic.vehicletender.data.model.db.*

@Dao
interface DAOAcessLocation {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertLocation(locationModelDB: LocationModelDB)

    @Query("select * from location order by idServer asc")
    fun getListLocation():LiveData<List<LocationModelDB>>

    @Query("select * from location ORDER BY id LIMIT 1")
    fun checkTableLocation():LiveData<LocationModelDB>

    @Query("delete from location")
    fun deleteLocationALL()

}

@Dao
interface  DAOAcessUser{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertUser(userModelDB: UserModelDB)

    @Query("select * from user where email = :email and password = :password")
    fun getUser(email:String, password: String):LiveData<UserModelDB>

    @Query("select * from user where email = :email ORDER BY id LIMIT 1 ")
    fun readUserEmail(email: String):LiveData<UserModelDB>

    @Query("select * from user")
    fun getUsersList():LiveData<List<UserModelDB>>

    @Query("select * from user where uuid =:uuid")
    fun getUserDateID(uuid:String):LiveData<UserModelDB>

    @Query("update  user set company_name = :company_name where id = :id")
    fun updateUserID(company_name:String, id:Int )

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(userModelDB: UserModelDB)

    @Query("select * from user where status_user = :status_user")
    fun getUserList(status_user:String):LiveData<List<UserModelDB>>

    @Query("select tenderUser.id as \"tenderUsersId\" , tenderUser.serverId as \"tenderUsersServerId\", tenderUser.tenderId as \"tenderUsersTenderId\", tenderUser.userId as \"tenderUsersUserId\", tender.idServer as \"tenderServerId\", tender.createdBy as \"tenderCretedBy\", tender.closeDate as \"tenderCloseDate\", tender.openDate as \"tenderOpenDate\",tender.statusId as \"tenderStatusId\", user.email as \"userEmail\", user.uuId as \"userUuid\", user.status_user as \"userStatususerl\", user.company_name as \"userCompanyName\", user.contact_name as \"userContactName\", user.contact_surname as \"userContactSurname\", user.contact_surname as \"userContactSurname\" from tenderUser left join tender on tenderUser.tenderId = tender.idServer left  join user on tenderUser.userId = User.uuId where user.status_user = :statusUser")
    fun getUserListUser(statusUser:String):LiveData<List<UserListUser>>

    @Query("select user.uuid, user.contact_name, user.contact_surname, user.status_user, user.email, user.company_name, tenderUser.tenderId, tenderUser.userId, tenderUser.serverId from user left join tenderUser on  tenderUser.userId = user.uuId where user.status_user = :statusUser")
    fun getUserListStatusId(statusUser:String):LiveData<List<UserListStatusId>>

    @Query("delete from user")
    fun deleteUserAll()
}

@Dao
interface DAOAcessTender{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertTender(tenderModelDB: TenderModelDB)

    @Query("select * from tender")
    fun getTender():LiveData<List<TenderModelDB>>

    @Query("select * from tender where statusId = :statusId")
    fun getTenderByStatus(statusId:Int):LiveData<List<TenderModelDB>>

    @Query ("select* from tender where tenderNo = :tenderNo" )
    fun getTenderByNo(tenderNo:String):LiveData<TenderModelDB>

    @Query("select * from tender where idServer = :serverId")
    fun getTenderModelId(serverId: Int):LiveData<TenderModelDB>

    @Query("delete from tender")
    fun deleteTenderAll()
}

@Dao
interface DAOAcessManafactura{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertManafactura(manafacturaModelDB: ManufacturerModelDB)

    @Query("select *  from manufacturer")
    fun getManufacturer():LiveData<List<ManufacturerModelDB>>

    @Query("delete from manufacturer")
    fun deleteManafacturaAll()
}

@Dao
interface DAOAcessCarModels{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertCarModels(carModelDB: CarModelDB)

    @Query ("select * from car_models")
    fun getCarModels():LiveData<List<CarModelDB>>

    @Query("select * from car_models where manufacturer_id =:manufacturer_id")
    fun getAllModelCarID(manufacturer_id:Int):LiveData<List<CarModelDB>>

    @Query("select car_models.IdServer,car_models.model_name, car_models.model_no, manufacturer.manufacturer_name from car_models left join manufacturer on car_models.manufacturer_id  = manufacturer.IdServer order by car_models.IdServer asc")
    fun getCarsInfo():LiveData<List<ManAndCarModel>>

    @Query("delete from car_models")
    fun deleteCarModelsAll()
}

@Dao
interface DAOAcessStockInfo{
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertStockInfo(stockInfoModelDB: StockInfoModelDB)

    @Query("select * from stockInfo ")
    fun getStockInfo():LiveData<List<StockInfoModelDB>>

    @Query("select stockInfo.id, stockInfo.serverId,  stockInfo.year, stockInfo.mileage, stockInfo.price, stockInfo.comments, stockInfo.regNo, stockInfo.isSold, location.city, car_models.model_name, car_models.model_no, manufacturer.manufacturer_name from stockInfo left join location on stockInfo.locationId = location.idServer left join car_models on stockInfo.modelLineId = car_models.IdServer left join manufacturer on car_models.manufacturer_id  = manufacturer.IdServer order by  isSold asc ,  stockInfo.id desc ")
    fun getStockInfoList():LiveData<List<stockCarList>>

    @Query("select stockInfo.id, stockInfo.serverId, stockInfo.year, stockInfo.mileage, stockInfo.price, stockInfo.comments, stockInfo.regNo, stockInfo.isSold, location.city, car_models.model_name, car_models.model_no, manufacturer.manufacturer_name from stockInfo left join location on stockInfo.locationId = location.idServer left join car_models on stockInfo.modelLineId = car_models.IdServer left join manufacturer on car_models.manufacturer_id  = manufacturer.IdServer where isSold =:isSold order by stockInfo.serverId desc")
    fun getStockCarActivesList(isSold:Boolean):LiveData<List<stockCarList>>

    @Query("select stockInfo.id, stockInfo.serverId,  stockInfo.year, stockInfo.mileage, stockInfo.price, stockInfo.comments, stockInfo.regNo, stockInfo.isSold, location.city, car_models.model_name, car_models.model_no, manufacturer.manufacturer_name, tenderStock.tenderId, tenderStock.isDeleted,  tenderStock.serverId as \"tenderStockServerId\"  from stockInfo left join location on stockInfo.locationId = location.idServer left join car_models on stockInfo.modelLineId = car_models.IdServer left join manufacturer on car_models.manufacturer_id  = manufacturer.IdServer left join tenderStock on stockInfo.serverId = tenderStock.stockId where isSold =:isSold order by stockInfo.id desc")
    fun getStockListCarUpdate(isSold: Boolean):LiveData<List<stockCarUpdate>>

    @Query("delete from stockInfo")
    fun deleteStockInfoAll()
}

@Dao
interface DAOAcessStatus{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertStatus(statusModelDB: StatusModelDB)

    @Query("select*from status order by idServer asc")
    fun getStatus():LiveData<List<StatusModelDB>>

    @Query("delete from status")
    fun deleteStatusAll()
}

@Dao
interface DAOAcessBid{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  InsertBid(bidModelDB: BidModelDB)

    @Query("select * from bid where stockId = :stockId")
    fun getBid(stockId:Int): LiveData<List<BidModelDB>>

    @Query("delete from bid where userId = :userID and stockId =:stockId ")
    fun deleteBid(userID:Int, stockId: Int)

    @Query("delete from bid")
    fun deleteBidAll()

    @Query ("select  bid.price, bid.isWinningPrice, bid.isActive, User.email, User.company_name, User.contact_surname, User.contact_name, User.phone from bid left join tenderUser on bid.userId = tenderUser.serverId left join User on tenderUser.userId = User.uuId where isWinningPrice = :isWinningPrice and tenderUser.tenderId = :tenderId") /**/
    fun bidUserWin(isWinningPrice:Boolean, tenderId: Int): LiveData<List<tenderWiningList>>

}

@Dao
interface  DAOAcessTenderStock{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertTenderStock(tenderStockModelDB: TenderStockModelDB)

    @Query("select * from tenderStock")
    fun getTenderStock():LiveData<List<TenderStockModelDB>>

    @Query("select * from tenderStock where stockId =:stockId and tenderId = :tenderId")
    fun getTenderStockId(stockId:Int, tenderId:String):LiveData<TenderStockModelDB>

    @Query("delete from tenderStock where stockId =:stockId and tenderId = :tenderId")
    fun deleteTenderStock(stockId:Int, tenderId:String)

    @Query("delete from tenderStock where serverId =:serverId")
    fun deleteTenderServerIDStock(serverId:Int)

    @Query("select tenderStock.id, tenderStock.stockId, tenderStock.tenderId, tenderStock.saleDate,stockInfo.id as stockInfoId, stockInfo.year, stockInfo.mileage, stockInfo.price, stockInfo.comments, stockInfo.regNo, stockInfo.isSold, location.city, location.zipCOde, car_models.model_name, car_models.model_no, manufacturer.manufacturer_name, tenderStock.serverId, tenderStock.isDeleted from tenderStock left join stockInfo on tenderStock.stockId =  stockInfo.serverId left join location on stockInfo.locationId = location.idServer left join car_models on stockInfo.modelLineId = car_models.IdServer left join manufacturer on car_models.manufacturer_id = manufacturer.IdServer where tenderId = :tenderId  ")
    fun getTenderID(tenderId:String):LiveData<List<TenderFullListID>>

    @Query("delete from tenderStock")
    fun deleteTenderStockALL()


}

@Dao
interface DAOAcessTenderUser{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertTenderUser(tenderUserModelDB: TenderUserModelDB)

    @Query("select* from tenderUser")
    fun getTenderUser():LiveData<List<TenderUserModelDB>>

    @Query("select * from tenderUser where tenderId =:serverId and userId = :uuid  ORDER BY id LIMIT 1")
    fun readTenderUser(uuid: String, serverId: Int):LiveData<TenderUserModelDB>

    @Query("delete from tenderUser")
    fun deleteTenderUserALL()
}

@Dao
interface DAOAcessUserToken{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertUserToken(tokenDB:TokenDB)

    @Query("delete from token_auto_login")
    fun deleteUserToken()

    @Query("select * from token_auto_login ORDER BY id desc  LIMIT 1 ")
    fun readUserToken():LiveData<TokenDB>



}

@Dao
interface DAOAcessRole{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertRole(roleDB: RoleDB)

    @Query("select * from role")
    fun readRole():LiveData<List<RoleDB>>

    @Query("delete from role")
    fun deleteRoleALL()
}
