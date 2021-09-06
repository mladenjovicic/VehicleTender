package com.mladenjovicic.vehicletender

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mladenjovicic.vehicletender.ui.admAct.ListUser.ListUserFragment
import com.mladenjovicic.vehicletender.ui.admAct.addCars.AddManafacturaAndCarFragment
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
        val addManafacturaAndCar = AddManafacturaAndCarFragment()
        val listUsers = ListUserFragment()


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerAdminPanel, AdminPanelActivityFragment.newInstance())
                .commitNow()
        }
        bottomNavigationViewAdmin.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.addLocation->setCurrentFragment(addLocation)
                R.id.addUser-> setCurrentFragment(addUser)
                R.id.addManafacturaAndCar ->setCurrentFragment(addManafacturaAndCar)
                R.id.listUser ->setCurrentFragment(listUsers)
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