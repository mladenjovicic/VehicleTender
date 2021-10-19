package com.mladenjovicic.vehicletender

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mladenjovicic.vehicletender.ui.mainAct.main.UserHomeFragment
import com.mladenjovicic.vehicletender.ui.mainAct.reports.ReportsFragment
import com.mladenjovicic.vehicletender.ui.mainAct.history.HistoryFragment
import com.mladenjovicic.vehicletender.ui.mainAct.mainAct.MainActivityViewModel

class MainActivity : AppCompatActivity() {
private lateinit var viewModel:MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelsProviderUtils.getMainAcitvityViewModel(this)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val mainFragment = UserHomeFragment()
        val reportsFragment = ReportsFragment()
        val historyFragment= HistoryFragment()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.containerTenderss, UserHomeFragment.newInstance())
                    .commitNow()
            bottomNavigationView.setSelectedItemId(R.id.home)
        }
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("UserDate", Context.MODE_PRIVATE)
        if(sharedPreferences.getString("uuidUser", "null").toString()==""){
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags =Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
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


    }
    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.containerTenderss,fragment)
            commit()
        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
        R.id.menu_sign_out->{
            logout()
             }
        }
        return super.onOptionsItemSelected(item)
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

    fun logout(){
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("UserDate", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("contact_name_user", "")
        editor.putString("contact_surname_user", "")
        editor.putString("email_user", "")
        editor.putString("status_user", "")
        editor.putString("id_location", "")
        editor.putString("phone", "")
        editor.putString("company_name", "")
        editor.putString("uuidUser", "")
        editor.apply()
        editor.commit()
        viewModel.deleteAll()
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags =Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
    }