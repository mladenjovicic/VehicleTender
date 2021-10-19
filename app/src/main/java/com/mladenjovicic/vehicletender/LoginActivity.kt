package com.mladenjovicic.vehicletender

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.mladenjovicic.vehicletender.ui.login.LoginViewModel
import java.util.*

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelsProviderUtils.getLoginViewModel(this)
        val editTextEmailUser = findViewById<EditText>(R.id.editTextEmailUser).text.clear()
        //val editTextUserPassword = findViewById<EditText>(R.id.editTextUserPassword).text.clear()
        //autoLogin()
        loginUser()


    }


    /*fun autoLogin(){
        viewModel.autoLogin()
        viewModel.tokenLiveData?.observe(this, Observer {
            //println("autologin " + it.access_token)

        })
    }*/
    fun addManufacturerJSON(Token:String){
        viewModel.getManufacturerJSON(Token)


        viewModel.requestState.observe(this) {
            if(it.pending)
                Log.e("Loading", "retrofit request is in progress, show loading spinner")
            if(it.successful){
                Log.e("Success", "retrofit request is successful")
                viewModel.manufacturerLiveData.observe(this) {
                    if(it.isNotEmpty()) {
                        Log.e("Retrofit fetched list", "update adapter manufacturer! ${it.toList()}")
                        for (i in 0..it.size-1) {
                            viewModel.addCarList(it[i].ID!!,it[i].ManufacturerName!!)
                        }
                    }else
                        Log.e("List is empty or null", "update view")

                }
            }
        }

    }
    fun addStatusJSON(Token:String){
        viewModel.getStatusJSON(Token)
        viewModel.requestState.observe(this) {
            if(it.pending)
                Log.e("Loading", "retrofit request is in progress, show loading spinner")
            if(it.successful){
                Log.e("Success", "retrofit request is successful")
                viewModel.statusLiveData.observe(this) {
                    if(it.isNotEmpty()) {
                        Log.e("Retrofit fetched list", "update adapter! ${it.toList()}")
                        for (i in 0..it.size-1) {
                            viewModel.addTenderStatus(it[i].id!!,it[i].type!!)
                        }
                    }else
                        Log.e("List is empty or null", "update view")
                }
            }
        }

    }
    fun addCarModelJSON(Token:String){
        viewModel.getCarModelsJSON(Token)
        viewModel.requestState.observe(this) {
            if(it.pending)
                Log.e("Loading", "retrofit request is in progress, show loading spinner")
            if(it.successful){
                Log.e("Success", "retrofit request is successful")
                viewModel.carModelLiveData.observe(this) {
                    if(it.isNotEmpty()) {
                        Log.e("Retrofit fetched list", "update adapter! ${it.toList()}")
                        for (i in 0..it.size-1) {
                            viewModel.addCarModelList(it[i].ID!!,it[i].ModelName!!,it[i].ModelNO!!, it[i].ManufacturerId!!)
                        }
                    }else
                        Log.e("List is empty or null", "update view")
                }
            }
        }

    }
    fun addTenderUserJSON(Token:String){
        viewModel.getTenderUserJSON(Token)
        viewModel.requestState.observe(this) {
            if(it.pending)
                Log.e("Loading", "retrofit request is in progress, show loading spinner")
            if(it.successful){
                Log.e("Success", "retrofit request is successful")
                viewModel.tenderUserLiveData.observe(this) {
                    if(it.isNotEmpty()) {
                        Log.e("Retrofit fetched list", "update adapter! ${it.toList()}")
                        for (i in 0..it.size-1) {
                            viewModel.addTenderUser(it[i].id!!,it[i].tenderId!!, it[i].userId!! )
                        }
                    }else
                        Log.e("List is empty or null", "update view")

                }
            }
        }
    }
    fun addTenderStockJSON(Token:String){
        viewModel.getTenderStockJSON(Token)
        viewModel.requestState.observe(this) {
            if(it.pending)
                Log.e("Loading", "retrofit request is in progress, show loading spinner")
            if(it.successful){
                Log.e("Success", "retrofit request is successful")
                viewModel.tenderStockLiveData.observe(this) {
                    if(it.isNotEmpty()) {
                        Log.e("Retrofit fetched list", "update adapter! ${it.toList()}")
                        for (i in 0..it.size-1) {

                            viewModel.addTenderStock(it[i].id!!,it[i].stockId!!, it[i].tenderId!!.toString(), it[i].saleDate, it[i].isDeleted!! )

                        }
                    }else
                        Log.e("List is empty or null", "update view")

                }
            }
        }
    }
    fun addBidJSON(Token:String){
        viewModel.getBidJSON(Token)
        viewModel.requestState.observe(this) {
            if(it.pending)
                Log.e("Loading", "retrofit request is in progress, show loading spinner")
            if(it.successful){
                Log.e("Success", "retrofit request is successful")
                viewModel.bidLiveData.observe(this) {
                    if(it.isNotEmpty()) {
                        Log.e("Retrofit fetched list", "update adapter! ${it.toList()}")
                        for (i in 0..it.size-1) {
                            if(it[i].isActive == true){
                            viewModel.addBid(it[i].ID!!,it[i].TUserId!!.toString(), it[i].TStockId!!, it[i].Price!!, it[i].IsWinningPrice!!, it[i].isActive!!)
                            }else{
                            viewModel.deleteBid(it[i].TUserId!!, it[i].TStockId!!, false)
                            }
                        }
                    }else
                        Log.e("List is empty or null", "update view")

                }
            }
        }
    }
    fun addTenderJSON(Token:String){
        viewModel.getTenderJSON(Token)
        viewModel.requestState.observe(this) {
            if(it.pending)
                Log.e("Loading", "retrofit request is in progress, show loading spinner")
            if(it.successful){
                Log.e("Success", "retrofit request is successful")
                viewModel.tenderLiveData.observe(this) {
                    if(it.isNotEmpty()) {
                        Log.e("Retrofit fetched list", "update adapter! ${it.toList()}")
                        for (i in 0..it.size-1) {

                            viewModel.addTender(it[i].id!!,it[i].createdDate!!, it[i].createdBy!!, it[i].tenderNo!!, it[i].openDate!!.take(10), it[i].closeDate!!.take(10), it[i].statusId!!)
                        }
                    }else
                        Log.e("List is empty or null", "update view")

                }}
        }
    }
    fun addCarStockJSON(Token:String){
        viewModel.getCarStockJSON(Token)
        viewModel.requestState.observe(this) {
            if(it.pending)
                Log.e("Loading", "retrofit request is in progress, show loading spinner")
            if(it.successful){
                Log.e("Success", "retrofit request is successful")
                viewModel.carStockLiveData.observe(this) {

                    if(it.isNotEmpty()) {
                        Log.e("Retrofit fetched list", "update adapter!  test ${it.toList()}")
                        for (i in 0..it.size-1) {
                            var year = 0
                            if(it[i].year == null){
                                year=9999
                            }else{
                                year = it[i].year!!
                            }
                            viewModel.addCarStock(it[i].id!!,year, it[i].modelLineId!!, it[i].mileage!!, it[i].price!!, it[i].comments!!, it[i].locationId!!, it[i].regNo!!, it[i].isSold!!)
                        }
                    }else
                        Log.e("List is empty or null", "update view test")

                }
            }

            else {
                Log.e("error", "retrofit request is ${it.errorMessage}")
            }
        }
    }
    fun addLocationJSON(Token:String){
        viewModel.getLocationsJSON(Token)
        viewModel.requestState.observe(this) {
            if(it.pending)
                Log.e("Loading", "retrofit request is in progress, show loading spinner")
            if(it.successful){
                Log.e("Success", "retrofit request is successful")
                viewModel.locationsLiveData.observe(this) {
                    if(it.isNotEmpty()) {
                        Log.e("Retrofit fetched list", "update adapter! ${it.toList()}")
                        for (i in 0..it.size-1) {
                            viewModel.addLocationList(it[i].Id!!,it[i].City!!,it[i].ZipCode!!)
                        }
                    }else
                        Log.e("List is empty or null", "update view")

                }
            }
        }
    }
    fun addUserList(token: String){
        viewModel.getUserList(token)
        viewModel.requestState.observe(this) {
            if(it.pending)
                Log.e("Loading", "retrofit request is in progress, show loading spinner")
            if(it.successful){
                Log.e("Success", "retrofit request is successful")

                viewModel.getUserList.observe(this, Observer {
                    if(it.isNotEmpty()){
                        var status_user =1
                        Log.e("Retrofit fetched list", "update adapter! ${it.toList()}")
                        for (i in 0..it.size-1){
                            when(it[i]?.RoleName){
                                "admin"->{ status_user = 2
                                }
                                "user"->status_user = 0
                            }
                            viewModel.addNewUser(it[i]?.ID!!, it[i]?.FirstName!!, it[i]?.LastName!!, it[i]?.Email!!,"", status_user, it[i]?.LocationId.toString(), "it[i].PhoneNumber",it[i]?.CompanyName!!)
                        }
                    }else{
                        Log.e("List is empty or null", "update view")
                    }
                })
            }
        }

    }
    fun addUserRols(token: String){
        viewModel.getUserRols(token)
        viewModel.requestState.observe(this) {
            if(it.pending)
                Log.e("Loading", "retrofit request is in progress, show loading spinner")
            if(it.successful){
                viewModel.userRoleLiveData.observe(this)  {
                    if(it.isNotEmpty()){
                        for (i in 0.. it.size-1){
                            viewModel.insertUserRole(it[i].ID, it[i].Name)
                        }
                    }
                }

                Log.e("Success", "retrofit request is successful rols")}
            else{Log.e("error", "retrofit request is error rols ${it.errorMessage}")}
        }
    }
    fun synicDate(token: String){
        addLocationJSON(token)
        addStatusJSON(token)
        addManufacturerJSON(token)
        addCarModelJSON(token)
        addTenderUserJSON(token)
        addTenderStockJSON(token)
        addBidJSON(token)
        addTenderJSON(token)
        addCarStockJSON(token)
        addUserRols(token)
    }

    fun loginUser(){
        val btnLoginUser = findViewById<Button>(R.id.btnLoginUser)
        val editTextEmailUser = findViewById<EditText>(R.id.editTextEmailUser)
        val editTextUserPassword = findViewById<EditText>(R.id.editTextUserPassword)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("UserDate", Context.MODE_PRIVATE)

        btnLoginUser.setOnClickListener {

            if (editTextEmailUser.text.isEmpty()) {
                Toast.makeText(this, "User email is empty", Toast.LENGTH_SHORT).show()
            } else {
                if (editTextUserPassword.text.isEmpty()) {
                    Toast.makeText(this, "User password is empty", Toast.LENGTH_SHORT).show()
                } else {
                    var token = ""
                    viewModel.getToken(editTextEmailUser.text.toString(), editTextUserPassword.text.toString())
                    viewModel.getTokenAPI.observe(this, Observer {

                        if(it!= null){

                            //viewModel.getUserList("${it?.token_type} ${it?.access_token}")
                            viewModel.addUserToken(it.access_token!!, it.token_type!!, it.expires_in!!, it.userName!!, it.issued!!, it.expires!!)
                            viewModel.readUserToken()
                            /*viewModel.tokenLiveData?.observe(this, Observer {
                                println("dev 238" + it.toString())
                            })*/
                            token = "${it?.token_type} ${it?.access_token}"

                            synicDate(token)
                            viewModel.getUserProfil("${it?.token_type} ${it?.access_token}", editTextEmailUser.text.toString())
                            viewModel.getUserProfil.observe(this, Observer {
                                val contact_name: String? = it?.FirstName
                                val contact_surname: String? = it?.LastName
                                val email: String? = it?.Email
                                val status_user: String? = it?.RoleName
                                val id_location: Int? = it?.LocationId
                                val phone: String? = it?.PhoneNumber
                                val company_name: String? = it?.CompanyName
                                val uuid: String = it?.ID!!
                                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                                //editor.putInt("id_user", id)
                                editor.putString("uuidUser", uuid)
                                editor.putString("contact_name_user", contact_name)
                                editor.putString("contact_surname_user", contact_surname)
                                editor.putString("email_user", email)
                                when(status_user){
                                    "admin"->{editor.putInt("status_user", 2)
                                        addUserList(token)
                                    }
                                    "user"->editor.putInt("status_user", 0)
                                }
                                editor.putString("id_location", id_location.toString())
                                editor.putString("phone", phone)
                                editor.putString("company_name", company_name)
                                editor.putString("token", token)
                                editor.apply()
                                editor.commit()

                                viewModel.addNewUser(
                                        uuid,
                                    contact_name!!,
                                    contact_surname!!,
                                    email!!,
                                    "null",
                                    2,
                                    id_location.toString(),
                                    "phone!!",
                                    company_name!!)
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)

                            })
                        }

                    })

                    viewModel.requestState.observe(this) {
                        if(it.pending)
                            Log.e("Loading", "retrofit request is in progress, show loading spinner")
                        if(it.successful)
                            Log.e("Success", "retrofit request is successful")
                        else {
                            Log.e("error", "retrofit request is ${it.errorMessage}")
                        }
                    }

    }}}}

}