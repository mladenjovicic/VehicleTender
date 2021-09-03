package com.mladenjovicic.vehicletender

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mladenjovicic.vehicletender.ui.admAct.addUser.AddUserViewModel
import com.mladenjovicic.vehicletender.ui.login.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginUser()
    }
    fun loginUser(){
        val btnLoginUser = findViewById<Button>(R.id.btnLoginUser)
        val editTextEmailUser = findViewById<EditText>(R.id.editTextEmailUser)
        val editTextUserPassword = findViewById<EditText>(R.id.editTextUserPassword)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


        btnLoginUser.setOnClickListener {
            if(editTextEmailUser.text.isEmpty()){
                Toast.makeText(this, "User email is empty", Toast.LENGTH_SHORT).show()
            }else{
                if(editTextUserPassword.text.isEmpty()){
                    Toast.makeText(this, "User password is empty", Toast.LENGTH_SHORT).show()
                }else{
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}