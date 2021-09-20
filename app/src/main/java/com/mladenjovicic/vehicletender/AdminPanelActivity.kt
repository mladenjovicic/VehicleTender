package com.mladenjovicic.vehicletender

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.mladenjovicic.vehicletender.ui.admAct.ListCarStock.ListCarStockFragment
import com.mladenjovicic.vehicletender.ui.admAct.ListUser.ListUserFragment
import com.mladenjovicic.vehicletender.ui.admAct.addCarStock.AddCarStockFragment
import com.mladenjovicic.vehicletender.ui.admAct.addCars.AddManafacturaAndCarFragment
import com.mladenjovicic.vehicletender.ui.admAct.addLocation.AddLocationFragment
import com.mladenjovicic.vehicletender.ui.admAct.addTender.addTenderFragment
import com.mladenjovicic.vehicletender.ui.admAct.addUser.AddUserFragment
import com.mladenjovicic.vehicletender.ui.admAct.listTender.ListTenderFragment

class AdminPanelActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_panel_activity2)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLay)
        val navView = findViewById<NavigationView>(R.id.nav_view)
        val addLocation= AddLocationFragment()
        val addUser = AddUserFragment()
        val addManafacturaAndCar = AddManafacturaAndCarFragment()
        val listUsers = ListUserFragment()
        val addTender = addTenderFragment()
        val listTender = ListTenderFragment()
        val addCarStock = AddCarStockFragment()
        val listCarStock = ListCarStockFragment()


        toggle = ActionBarDrawerToggle(this, drawerLayout,R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerAdminPanel, ListTenderFragment.newInstance())
                .commitNow()
            setCurrentFragment(listTender)
        }

        navView.setNavigationItemSelectedListener {
            it.isChecked = true
            drawerLayout.closeDrawers()

            when(it.itemId){
                R.id.addLocation1->setCurrentFragment(addLocation)
                R.id.addUser1-> setCurrentFragment(addUser)
                R.id.addManafacturaAndCar1 ->setCurrentFragment(addManafacturaAndCar)
                R.id.addCarStock1 ->setCurrentFragment(addCarStock)
                R.id.listCarStock1 ->setCurrentFragment(listCarStock)
                R.id.listUser1 ->setCurrentFragment(listUsers)
                R.id.addTender1 ->setCurrentFragment(addTender)
                R.id.listTender1 -> setCurrentFragment(listTender)

            }
            true

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.containerAdminPanel,fragment)
            commit()
        }
    }


}