package com.mladenjovicic.vehicletender

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mladenjovicic.vehicletender.ui.mainAct.main.MainFragment
import com.mladenjovicic.vehicletender.ui.mainAct.reports.ReportsFragment
import com.mladenjovicic.vehicletender.ui.mainAct.history.HistoryFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val mainFragment = MainFragment()
        val reportsFragment = ReportsFragment()
        val historyFragment= HistoryFragment()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.containerTenderss, MainFragment.newInstance())
                    .commitNow()
            bottomNavigationView.setSelectedItemId(R.id.home)
        }


        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> setCurrentFragment(mainFragment)
                R.id.report -> setCurrentFragment(reportsFragment)
                R.id.history -> setCurrentFragment(historyFragment)
            }
            true
        }
        adminPanel()
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("UserDate", Context.MODE_PRIVATE)


    }
    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.containerTenderss,fragment)
            commit()
        }

    fun adminPanel(){
        val fabAdmin = findViewById<FloatingActionButton>(R.id.fabAdmin)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("UserDate", Context.MODE_PRIVATE)

        if(sharedPreferences.getInt("status_user",5)==2){
            fabAdmin.visibility = View.VISIBLE
        }else{
            fabAdmin.visibility = View.GONE
        }
        fabAdmin.setOnClickListener {
            val intent = Intent(this, AdminPanelActivity::class.java)
            startActivity(intent)
        }
    }
    }