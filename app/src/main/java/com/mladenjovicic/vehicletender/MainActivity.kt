package com.mladenjovicic.vehicletender

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mladenjovicic.vehicletender.ui.mainAct.main.MainFragment
import com.mladenjovicic.vehicletender.ui.mainAct.reports.ReportsFragment
import com.mladenjovicic.vehicletender.ui.mainAct.sec.SecFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val mainFragment = MainFragment()
        val reportsFragment = ReportsFragment()
        val secFragment= SecFragment()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
            bottomNavigationView.setSelectedItemId(R.id.home)
        }


        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> setCurrentFragment(mainFragment)
                R.id.report -> setCurrentFragment(reportsFragment)
                R.id.act1 -> setCurrentFragment(secFragment)
            }
            true
        }
        adminPanel()


    }
    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container,fragment)
            commit()
        }

    fun adminPanel(){
        val fabAdmin = findViewById<FloatingActionButton>(R.id.fabAdmin)

        fabAdmin.setOnClickListener {
            val intent = Intent(this, AdminPanelActivity::class.java)
            startActivity(intent)
        }
    }
    }