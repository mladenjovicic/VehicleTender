package com.mladenjovicic.vehicletender

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import com.mladenjovicic.vehicletender.ui.login.LoginViewModelTemp
import java.util.*

class LoginActivityTemp:AppCompatActivity() {
    private lateinit var viewModel: LoginViewModelTemp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelsProviderUtil.getLoginViewModel(this)
        subscribeUI()
}
    private fun subscribeUI() {

        addData()
        loginUser()

        viewModel.locationsLiveData.observe(this) {
            if(it.isNotEmpty())
                Log.e("Retrofit fetched list", "update adapter! ${it.toList()}")
            else
                Log.e("List is empty or null", "update view")

        }

        viewModel.requestState.observe(this) {
            if(it.pending)
                Log.e("Loading", "retrofit request is in progress, show loading spinner")
            if(it.successful)
                Log.e("Success", "retrofit request is successful")
        }

    }

    fun addData() {
        val listCity = arrayListOf<String>("Banja Luka", "Beograd", "Zagreb", "Sarajevo")
        val listZip = arrayListOf<String>("78000", "11000", "10000", "73000")
        val listStatus = arrayListOf<String>("Open", "Close")
        val listCar = arrayListOf<String>(
                "Audi",
                "VW",
                "Skoda",
                "Seat",
                "Renault",
                "Peugeot",
                "BMW",
                "Opel",
                "Smart",
                "Porsche",
                "FIAT",
                "Alfa Romeo",
                "Lancia",
                "Ferrari",
                "Ford",
                "Lamborghini",
                "Toyota",
                "Honda",
                "Suzuki",
                "Lada",
                "Zastava",
                "Rimac"
        )


        viewModel.checkTableUser()
        viewModel.userModelDB?.observe(this, Observer {
            if (it == null) {
                println("deb90" + listCity + " " + listZip)
                for (i in 0..listCity.size - 1) {
                    println("deb91" + listCity[i] + " " + listZip[i])
                    viewModel.addLocationList(listCity[i], listZip[i])
                }
                println("deb92" + listCar)
                for (i in 0..listCar.size - 1) {
                    println("deb93" + listCar[i])
                    viewModel.addCarList(listCar[i])
                }
                viewModel.addTenderStatus("Close")
                viewModel.addTenderStatus("Open")

                println("deb94" + listStatus)
                /*for (i in 0..listStatus.size-1){
                    println("deb95" + listStatus[i])
                    viewModel.addTenderStatus(this, listStatus[i])
                }*/

                viewModel.addNewUser(
                        UUID.randomUUID().toString(),
                        "Mladen",
                        "Jovicic",
                        "a@a.com",
                        "1",
                        2,
                        "1",
                        "066497862",
                        "Axelyos"
                )
            }
        })
    }

    private fun loginUser() {
        val btnLoginUser = findViewById<Button>(R.id.btnLoginUser)
        val editTextEmailUser = findViewById<EditText>(R.id.editTextEmailUser)
        val editTextUserPassword = findViewById<EditText>(R.id.editTextUserPassword)
        val sharedPreferences: SharedPreferences =
                this.getSharedPreferences("UserDate", Context.MODE_PRIVATE)


        btnLoginUser.setOnClickListener {
            println("radi li")
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
                }
            }
        }
    }
}