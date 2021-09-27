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
import androidx.lifecycle.Observer
import com.mladenjovicic.vehicletender.data.model.api.GetTokenAPI
import com.mladenjovicic.vehicletender.ui.login.LoginViewModel
import java.util.*

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelsProviderUtils.getLoginViewModel(this)
        addData()
        loginUser()

    }

    fun addData(){
        //addLocationJSON()

        viewModel.checkTableUser()
        viewModel.userModelDB?.observe(this, Observer {

            if (it == null) {

                viewModel.addNewUser(UUID.randomUUID().toString(),
                    "Mladen",
                    "Jovicic",
                    "a@a.com",
                    "1",
                    2,
                    "1",
                    "066497862",
                    "Axelyos")
            }
        })

    }
    fun addManufacturerJSON(Token:String){
        viewModel.getManufacturerJSON(Token)
        viewModel.manufacturerLiveData.observe(this) {
            if(it.isNotEmpty()) {
                Log.e("Retrofit fetched list", "update adapter! ${it.toList()}")
                for (i in 0..it.size-1) {
                    viewModel.addCarList(it[i].ID!!,it[i].ManufacturerName!!)
                }
            }else
                Log.e("List is empty or null", "update view")

        }

        viewModel.requestState.observe(this) {
            if(it.pending)
                Log.e("Loading", "retrofit request is in progress, show loading spinner")
            if(it.successful)
                Log.e("Success", "retrofit request is successful")
        }

    }
    fun addStatusJSON(Token:String){
        viewModel.getStatusJSON(Token)
        viewModel.statusLiveData.observe(this) {
            if(it.isNotEmpty()) {
                Log.e("Retrofit fetched list", "update adapter! ${it.toList()}")
                for (i in 0..it.size-1) {
                    viewModel.addTenderStatus(it[i].id!!,it[i].type!!)
                }
            }else
                Log.e("List is empty or null", "update view")

        }

        viewModel.requestState.observe(this) {
            if(it.pending)
                Log.e("Loading", "retrofit request is in progress, show loading spinner")
            if(it.successful)
                Log.e("Success", "retrofit request is successful")
        }

    }
    fun addCarModelJSON(Token:String){
        viewModel.getCarModelsJSON(Token)

        viewModel.carModelLiveData.observe(this) {
            if(it.isNotEmpty()) {
                Log.e("Retrofit fetched list", "update adapter! ${it.toList()}")
                for (i in 0..it.size-1) {
                    viewModel.addCarModelList(it[i].ID!!,it[i].ModelName!!,it[i].ModelNO!!, it[i].ManufacturerId!!)
                }
            }else
                Log.e("List is empty or null", "update view")

        }

        viewModel.requestState.observe(this) {
            if(it.pending)
                Log.e("Loading", "retrofit request is in progress, show loading spinner")
            if(it.successful)
                Log.e("Success", "retrofit request is successful")
        }

    }
    fun addTenderUserJSON(Token:String){
        viewModel.getTenderUserJSON(Token)
        viewModel.tenderUserLiveData.observe(this) {
            if(it.isNotEmpty()) {
                Log.e("Retrofit fetched list", "update adapter! ${it.toList()}")
                for (i in 0..it.size-1) {
                   // viewModel.addLocationList(it[i].id!!,it[i].city!!,it[i].zipCOde!!)
                    viewModel.addTenderUser(it[i].id!!,it[i].tenderId!!, it[i].userId!! )
                }
            }else
                Log.e("List is empty or null", "update view")

        }
        viewModel.requestState.observe(this) {
            if(it.pending)
                Log.e("Loading", "retrofit request is in progress, show loading spinner")
            if(it.successful)
                Log.e("Success", "retrofit request is successful")
        }
    }
    fun addTenderStockJSON(Token:String){
        viewModel.getTenderStockJSON(Token)
        viewModel.tenderStockLiveData.observe(this) {
            if(it.isNotEmpty()) {
                Log.e("Retrofit fetched list", "update adapter! ${it.toList()}")
                for (i in 0..it.size-1) {
                    // viewModel.addLocationList(it[i].id!!,it[i].city!!,it[i].zipCOde!!)
                    viewModel.addTenderStock(it[i].id!!,it[i].stockId!!, it[i].tenderId!!, it[i].saleDate!! )
                }
            }else
                Log.e("List is empty or null", "update view")

        }
        viewModel.requestState.observe(this) {
            if(it.pending)
                Log.e("Loading", "retrofit request is in progress, show loading spinner")
            if(it.successful)
                Log.e("Success", "retrofit request is successful")
        }
    }
    fun addBidJSON(Token:String){
        viewModel.getBidJSON(Token)
        viewModel.bidLiveData.observe(this) {
            if(it.isNotEmpty()) {
                Log.e("Retrofit fetched list", "update adapter! ${it.toList()}")
                for (i in 0..it.size-1) {

                    viewModel.addBid(it[i].ID!!,it[i].TUserId!!, it[i].TStockId!!, it[i].Price!!, it[i].IsWinningPrice!!)
                }
            }else
                Log.e("List is empty or null", "update view")

        }
        viewModel.requestState.observe(this) {
            if(it.pending)
                Log.e("Loading", "retrofit request is in progress, show loading spinner")
            if(it.successful)
                Log.e("Success", "retrofit request is successful")
        }
    }
    fun addTenderJSON(Token:String){
        viewModel.getTenderJSON(Token)
        viewModel.tenderLiveData.observe(this) {
            if(it.isNotEmpty()) {
                Log.e("Retrofit fetched list", "update adapter! ${it.toList()}")
                for (i in 0..it.size-1) {

                    viewModel.addTender(it[i].id!!,it[i].createdDate!!, it[i].createdBy!!, it[i].tenderNo!!, it[i].openDate!!, it[i].closeDate!!, it[i].statusId!!)
                }
            }else
                Log.e("List is empty or null", "update view")

        }
        viewModel.requestState.observe(this) {
            if(it.pending)
                Log.e("Loading", "retrofit request is in progress, show loading spinner")
            if(it.successful)
                Log.e("Success", "retrofit request is successful")
        }
    }
    fun addCarStockJSON(Token:String){
        viewModel.carStockLiveData.observe(this) {
            viewModel.getCarStockJSON(Token)
            if(it.isNotEmpty()) {
                Log.e("Retrofit fetched list", "update adapter! ${it.toList()}")
                for (i in 0..it.size-1) {

                    viewModel.addCarStock(it[i].id!!,it[i].year!!, it[i].modelLineId!!, it[i].mileage!!, it[i].price!!, it[i].comments!!, it[i].locationId!!, it[i].regNo!!, it[i].isSold!!)
                }
            }else
                Log.e("List is empty or null", "update view")

        }
        viewModel.requestState.observe(this) {
            if(it.pending)
                Log.e("Loading", "retrofit request is in progress, show loading spinner")
            if(it.successful)
                Log.e("Success", "retrofit request is successful")
        }
    }
    fun addLocationJSON(Token:String){

        viewModel.getLocationsJSON(Token)

        viewModel.locationsLiveData.observe(this) {
            if(it.isNotEmpty()) {
                Log.e("Retrofit fetched list", "update adapter! ${it.toList()}")
                for (i in 0..it.size-1) {
                    viewModel.addLocationList(it[i].Id!!,it[i].City!!,it[i].ZipCode!!)
                }
            }else
                Log.e("List is empty or null", "update view")

        }

        viewModel.requestState.observe(this) {
            if(it.pending)
                Log.e("Loading", "retrofit request is in progress, show loading spinner")
            if(it.successful)
                Log.e("Success", "retrofit request is successful")
        }
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
                    viewModel.getToken(editTextEmailUser.text.toString(), editTextUserPassword.text.toString())
                    viewModel.getTokenAPI.observe(this, Observer {
                        println("test 236" + it.toString())
                        if(it!= null){
                            viewModel.getUserList("${it?.token_type} ${it?.access_token}")
                            println("test 236" +"${it?.token_type} ${it?.access_token}")
                            addLocationJSON("${it?.token_type} ${it?.access_token}")
                            addStatusJSON("${it?.token_type} ${it?.access_token}")
                            addManufacturerJSON("${it?.token_type} ${it?.access_token}")
                            addCarModelJSON("${it?.token_type} ${it?.access_token}")
                            addTenderUserJSON("${it?.token_type} ${it?.access_token}")
                            addTenderStockJSON("${it?.token_type} ${it?.access_token}")
                            addBidJSON("${it?.token_type} ${it?.access_token}")
                            addTenderJSON("${it?.token_type} ${it?.access_token}")
                            addCarStockJSON("${it?.token_type} ${it?.access_token}")

                            viewModel.getUserListTest.observe(this, Observer {
                                for (i in 0..it.size!!-1){
                                    println("test 237" + it[i].toString())
                                    if(it[i]?.Email == editTextEmailUser.text.toString()){
                                        //val id: Int = Integer.parseInt(it[i]?.Id.toString())
                                        val contact_name: String? = it[i]?.FirstName
                                        val contact_surname: String? = it[i]?.LastName
                                        val email: String? = it[i]?.Email
                                        val status_user: String? = it[i]?.RoleId
                                        val id_location: String? = it[i]?.City
                                        val phone: String? = it[i]?.PhoneNumber
                                        val company_name: String? = it[i]?.DealerName
                                        //val uuid: String = it[i]?.uuId
                                        val editor: SharedPreferences.Editor = sharedPreferences.edit()
                                        //editor.putInt("id_user", id)
                                        //editor.putString("uuidUser", uuid)
                                        editor.putString("contact_name_user", contact_name)
                                        editor.putString("contact_surname_user", contact_surname)
                                        editor.putString("email_user", email)
                                        editor.putString("status_user", status_user)
                                        editor.putString("id_location", id_location)
                                        editor.putString("phone", phone)
                                        editor.putString("company_name", company_name)
                                        editor.apply()
                                        editor.commit()
                                        /*val intent = Intent(this, MainActivity::class.java)
                                        startActivity(intent)*/
                                    }
                                }

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


                    /*viewModel.checkUser(
                        editTextEmailUser.text.toString(),
                        editTextUserPassword.text.toString()
                    )
                    viewModel.userModelDB?.observe(this, Observer {
                        if (it != null) {
                            println("dev54" + it.toString())
                            val id: Int = Integer.parseInt(it.Id.toString())
                            val contact_name: String = it.contact_name
                            val contact_surname: String = it.contact_surname
                            val email: String = it.email
                            val status_user: Int = it.status_user
                            val id_location: String = it.id_location
                            val phone: String = it.phone
                            val company_name: String = it.company_name
                            val uuid: String = it.uuId
                            val editor: SharedPreferences.Editor = sharedPreferences.edit()
                            editor.putInt("id_user", id)
                            editor.putString("uuidUser", uuid)
                            editor.putString("contact_name_user", contact_name)
                            editor.putString("contact_surname_user", contact_surname)
                            editor.putString("email_user", email)
                            editor.putInt("status_user", status_user)
                            editor.putString("id_location", id_location)
                            editor.putString("phone", phone)
                            editor.putString("company_name", company_name)
                            editor.apply()
                            editor.commit()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                        }
                    })*/
    }}}}

}