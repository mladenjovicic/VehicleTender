package com.mladenjovicic.vehicletender

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mladenjovicic.vehicletender.ui.admAct.addLocation.AddLocationFragment
import com.mladenjovicic.vehicletender.ui.admAct.addUser.AddUserFragment
import com.mladenjovicic.vehicletender.ui.admAct.admin.AdminPanelActivityFragment

class AdminPanelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_panel_activity2_activity)
        val bottomNavigationViewAdmin = findViewById<BottomNavigationView>(R.id.bottomNavigationViewAdmin)
        val addLocation= AddLocationFragment()
        val addUser = AddUserFragment()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerAdminPanel, AdminPanelActivityFragment.newInstance())
                .commitNow()
        }
        bottomNavigationViewAdmin.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.addLocation->setCurrentFragment(addLocation)
                R.id.addUser-> setCurrentFragment(addUser)
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.containerAdminPanel,fragment)
            commit()
        }
}