package com.mladenjovicic.vehicletender

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mladenjovicic.vehicletender.ui.updateUser.UserUpdateViewModel

class UpdateUserActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var statusUser = 0
    var userLoc = 0
    private lateinit var viewModel: UserUpdateViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_user_activity)
        viewModel = ViewModelProvider(this).get(UserUpdateViewModel::class.java)
        var userId = ""
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

        val adapterUserStatus = ArrayAdapter.createFromResource(this, R.array.user_staus, R.layout.spinner_item)
        adapterUserStatus.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerUserStatusUpdate?.adapter = adapterUserStatus
        spinnerUserStatusUpdate?.onItemSelectedListener = this

        val listLocation = this?.let {
            ArrayAdapter<Any>(it, R.layout.spinner_item)
        }
        viewModel.getListLocation(this)?.observe(this, Observer { location->
            location.forEach {
                listLocation?.add(it.city + " " + it.zipCode)  }
        })

        listLocation?.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerUserLocationUpdate?.adapter = listLocation
        spinnerUserLocationUpdate?.onItemSelectedListener = this


        userId= intent!!.extras?.get("uuid") as String

        viewModel.getUserDateID(this, userId)
        viewModel.userModelDB?.observe(this, Observer {
            if(it!=null){
                textViewIDUserUpdate.text = "User id: "+ userId
                editTextUserNameUpdate.setText(it.contact_name)
                editTextSurnameUserUpdate.setText(it.contact_surname)
                editTextEmailUserUpdate.setText(it.email)
                editTextPasswordUpdate.setText(it.password)
                editTextPhoneUpdate.setText(it.phone)
                editTextCompanyUserUpdate.setText(it.company_name)
                spinnerUserStatusUpdate.setSelection(it.status_user)
                spinnerUserLocationUpdate.setSelection((it.id_location.toInt()))

            }
        })


        btnUpdateUser.setOnClickListener {
            viewModel.updateUser(this,userId, editTextUserNameUpdate.text.toString(),editTextSurnameUserUpdate.text.toString(), editTextEmailUserUpdate.text.toString(),editTextPasswordUpdate.text.toString(),statusUser, userLoc.toString(), editTextPhoneUpdate.text.toString(),editTextCompanyUserUpdate.text.toString()   )
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        when(parent?.id){
            R.id.spinnerUserStatusUpdate -> statusUser = pos
            R.id.spinnerUserLocationUpdate-> userLoc = pos
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}