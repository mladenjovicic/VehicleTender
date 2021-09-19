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
import androidx.lifecycle.ViewModelProvider
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
        viewModel.checkTableUser()
        viewModel.userModelDB?.observe(this, Observer {

            if (it == null) {
                addLocation()
                addStatus()
                addManufacturer()
                addCarModel()
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
    fun addManufacturer(){

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
    fun addStatus(){

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
    fun addCarModel(){

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
    fun addLocation(){

        viewModel.locationsLiveData.observe(this) {
            if(it.isNotEmpty()) {
                Log.e("Retrofit fetched list", "update adapter! ${it.toList()}")
                for (i in 0..it.size-1) {
                    viewModel.addLocationList(it[i].id!!,it[i].city!!,it[i].zipCOde!!)
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
        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences("UserDate", Context.MODE_PRIVATE)
        btnLoginUser.setOnClickListener {
            if (editTextEmailUser.text.isEmpty()) {
                Toast.makeText(this, "User email is empty", Toast.LENGTH_SHORT).show()
            } else {
                if (editTextUserPassword.text.isEmpty()) {
                    Toast.makeText(this, "User password is empty", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.checkUser(
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
                    })
    }}}}

    /*fun addDate(){

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.checkTableUser(this)
        viewModel.userModelDB?.observe(this, Observer {
            if (it==null){

                viewModel.parsetJSONLocation(this,0)
                viewModel.parsetJSONStatus(this, 0)
                viewModel.parsetJSONCarModel(this, 0)
                viewModel.parsetJSONManufacturer(this, 0)

                 viewModel.addNewUser(this,
                    UUID.randomUUID().toString(), "Mladen", "Jovicic", "a@a.com", "1", 2, "1","066497862", "Axelyos")
            }
        })
    }
    fun loginUser(){
        val btnLoginUser = findViewById<Button>(R.id.btnLoginUser)
        val editTextEmailUser = findViewById<EditText>(R.id.editTextEmailUser)
        val editTextUserPassword = findViewById<EditText>(R.id.editTextUserPassword)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val sharedPreferences:SharedPreferences = this.getSharedPreferences("UserDate", Context.MODE_PRIVATE)


        btnLoginUser.setOnClickListener {
            if(editTextEmailUser.text.isEmpty()){
                Toast.makeText(this, "User email is empty", Toast.LENGTH_SHORT).show()
            }else{
                if(editTextUserPassword.text.isEmpty()){
                    Toast.makeText(this, "User password is empty", Toast.LENGTH_SHORT).show()
                }else{
                    viewModel.checkUser(this, editTextEmailUser.text.toString(), editTextUserPassword.text.toString())
                    viewModel.userModelDB?.observe( this, Observer {
                        if(it!= null){
                            val id:Int = Integer.parseInt(it.Id.toString())
                            val contact_name:String = it.contact_name
                            val contact_surname:String = it.contact_surname
                            val email:String = it.email
                            val status_user:Int = it.status_user
                            val id_location:String = it.id_location
                            val phone:String = it.phone
                            val company_name:String = it.company_name
                            val uuid:String = it.uuId
                            val editor:SharedPreferences.Editor = sharedPreferences.edit()
                            editor.putInt("id_user", id)
                            editor.putString("uuidUser", uuid)
                            editor.putString("contact_name_user", contact_name)
                            editor.putString("contact_surname_user", contact_surname)
                            editor.putString("email_user",email )
                            editor.putInt("status_user", status_user)
                            editor.putString("id_location",id_location)
                            editor.putString("phone", phone)
                            editor.putString("company_name",company_name)
                            editor.apply()
                            editor.commit()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }
        }
    }*/
}