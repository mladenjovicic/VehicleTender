package com.mladenjovicic.vehicletender

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mladenjovicic.vehicletender.ui.winBid.WinBidFragment

class WinBidActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win_bid)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WinBidFragment.newInstance())
                .commitNow()
        }

        val sharedPreferences: SharedPreferences = this.getSharedPreferences("UserDate", Context.MODE_PRIVATE)
        if(sharedPreferences.getString("uuidUser", "null").toString()==""){
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}