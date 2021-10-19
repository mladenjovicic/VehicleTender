package com.mladenjovicic.vehicletender

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import com.mladenjovicic.vehicletender.ui.updateUser.UserUpdateViewModel

class UpdateUserActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var statusUser = ""
    var userLoc = 0
    var rolaName =""
    private lateinit var viewModel: UserUpdateViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user)
        viewModel = ViewModelsProviderUtils.getUpdteUserViewMode(this)
        var userId = ""
        var userLocationId = "0"
        val textViewIDUserUpdate = findViewById<TextView>(R.id.textViewIDUserUpdate)
        val editTextUserNameUpdate = findViewById<EditText>(R.id.editTextUserNameUpdate)
        val editTextSurnameUserUpdate = findViewById<EditText>(R.id.editTextSurnameUserUpdate)
        val editTextEmailUserUpdate = findViewById<EditText>(R.id.editTextEmailUserUpdate)
        val editTextPasswordUpdate = findViewById<EditText>(R.id.editTextPasswordUpdate)
        val editTextPhoneUpdate = findViewById<EditText>(R.id.editTextPhoneUpdate)
        val editTextCompanyUserUpdate = findViewById<EditText>(R.id.editTextCompanyUserUpdate)
        val spinnerUserLocationUpdate = findViewById<Spinner>(R.id.spinnerUserLocationUpdate)
        val spinnerUserStatusUpdate = findViewById<Spinner>(R.id.spinnerUserStatusUpdate)
        val btnUpdateUser = findViewById<Button>(R.id.btnUpdateUser)

        val sharedPreferences: SharedPreferences = this.getSharedPreferences("UserDate", Context.MODE_PRIVATE)
        var token = sharedPreferences.getString("token", "null").toString()
        if(sharedPreferences.getString("uuidUser", "null").toString()==""){
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        val listLocation = this.let {
            ArrayAdapter<Any>(it, R.layout.spinner_item)
        }
        val listRole =this?.let {
            ArrayAdapter<Any>(it, R.layout.spinner_item)
        }

        var count = 0
        userLocationId= intent!!.extras?.get("userLocation") as String

        viewModel.getListLocation()?.observe(this, Observer { location->
            location.forEach {
                listLocation.add(it.city + " " + it.zipCOde)
                if (it.idServer == userLocationId.toInt() ){
                    spinnerUserLocationUpdate.setSelection(count)
                }
                count++
            }
        })

        viewModel.getListUserRole()?.observe(this,{role->
            role?.forEach {
                listRole?.add(it.RoleId)
            }
        })

        listLocation.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerUserLocationUpdate?.adapter = listLocation
        spinnerUserLocationUpdate?.onItemSelectedListener = this

        listRole?.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerUserStatusUpdate?.adapter = listRole
        spinnerUserStatusUpdate?.onItemSelectedListener = this


        userId= intent!!.extras?.get("uuid") as String

        viewModel.getUserDateID(userId)
        viewModel.userModelDB?.observe(this, Observer {
            if(it!=null){
                textViewIDUserUpdate.text = "User id: "+ userId
                editTextUserNameUpdate.setText(it.contact_name)
                editTextSurnameUserUpdate.setText(it.contact_surname)
                editTextEmailUserUpdate.setText(it.email)
                editTextPasswordUpdate.setText(it.password)
                editTextPhoneUpdate.setText(it.phone)
                editTextCompanyUserUpdate.setText(it.company_name)
                var statusLine = -1
                when(it.status_user){
                    0->{statusLine = 1}
                    2->{statusLine = 0}
                }
                spinnerUserStatusUpdate.setSelection(statusLine)
            }
        })

        btnUpdateUser.setOnClickListener {
            if(editTextCompanyUserUpdate.text.isNotEmpty()&& editTextEmailUserUpdate.text.isNotEmpty()&&editTextSurnameUserUpdate.text.isNotEmpty()&&editTextUserNameUpdate.text.isNotEmpty()){
                viewModel.updateUserJSON(token,userId,editTextEmailUserUpdate.text.toString(),"",userLoc,true, editTextUserNameUpdate.text.toString(), editTextSurnameUserUpdate.text.toString(),
                        editTextPhoneUpdate.text.toString(),rolaName, statusUser, editTextCompanyUserUpdate.text.toString(),editTextPasswordUpdate.text.toString())

                viewModel.requestState.observe(this){
                    if(it.pending)
                        Log.e("Loading update user", "retrofit request is in progress, show loading spinner")
                    if(it.successful){
                        Log.e("Success update user", "retrofit request is successful update user")
                        viewModel.getUpdateUserObserver().observe(this, Observer {
                            if(it!= null){
                                Toast.makeText(this,"Update user is successful", Toast.LENGTH_SHORT).show()
                                var  status_user = -1
                                when(it?.RoleName){
                                    "admin"->status_user = 2
                                    "user"->status_user = 0
                                }
                                viewModel.updateUser(it.ID!!, it.FirstName!!, it.LastName!!, it.Email!!,"", status_user, it.LocationId.toString(),"it.PhoneNumber", it.CompanyName!!)
                            }
                        })
                    }
                    else{
                        Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        when(parent?.id){
            R.id.spinnerUserStatusUpdate -> {getRoleID(pos)}
            R.id.spinnerUserLocationUpdate-> {getLocationID(pos)}
        }
    }

    fun getLocationID(pos: Int){
        viewModel.getListLocation()?.observe(this, Observer { location->
            location.forEach {
                userLoc = location[pos].idServer!!}
        })
    }

    fun getRoleID(pos: Int){
        viewModel.getListUserRole()?.observe(this, {role->
            role?.forEach {
                statusUser = role[pos].ServerId
                rolaName = role[pos].RoleId
                println("dev0021" + statusUser + " " + role[pos].RoleId)

            }

        })
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}