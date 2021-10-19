package com.mladenjovicic.vehicletender

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mladenjovicic.vehicletender.adapter.CarsForSellingAdapter
import com.mladenjovicic.vehicletender.ui.mainAct.main.UserHomeFragment
import com.mladenjovicic.vehicletender.ui.tender.AddTenderUser.AddTenderUserFragment
import com.mladenjovicic.vehicletender.ui.tender.addTenderStock.AddTenderStockFragment
import com.mladenjovicic.vehicletender.ui.tender.addTenderStock.TenderUseViewModel

class TenderUseActivity2 : AppCompatActivity() {
    private lateinit var viewModel: TenderUseViewModel
    lateinit var recyclerViewCarsForSelling: CarsForSellingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tender_use2)
        //viewModel = ViewModelProvider(this).get(TenderUseViewModel::class.java)
        viewModel = ViewModelsProviderUtils.getTenderViewModel(this)
        val bottomNavigationViewTender = findViewById<BottomNavigationView>(R.id.bottomNavigationViewTender)
        val addTenderUserFragment = AddTenderUserFragment()
        val addTenderStockFragment = AddTenderStockFragment()
        val textViewTest123 = findViewById<TextView>(R.id.textViewTest123)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerTenderss, AddTenderStockFragment.newInstance())
                .commitNow()
            bottomNavigationViewTender.setSelectedItemId(R.id.tenderStock)
        }

        bottomNavigationViewTender.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.tenderStock->setCurrentFragment(addTenderStockFragment)
                R.id.userTender -> setCurrentFragment(addTenderUserFragment)
            }
            true
        }
        var tenderNo = ""
        var statusId = -1
        var saleDate = ""
        var tenderId = -1
        var token = ""

        val sharedPreferences: SharedPreferences = this.getSharedPreferences("UserDate", Context.MODE_PRIVATE)

        if(sharedPreferences.getString("uuidUser", "null").toString()==""){
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags =Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        token = sharedPreferences.getString("token", "null").toString()

        tenderNo= intent!!.extras?.get("tenderNo") as String
        tenderId = intent!!.extras?.get("tenderId") as Int

        statusId = intent!!.extras?.get("statusId") as Int

        viewModel.getTenderBYTenderNo( tenderNo)
        viewModel.getStockCarList( false)
        viewModel.getStockCarUpdate(false)


        viewModel.tenderModelDB?.observe(this,{
            if(it!=null){
                textViewTest123.text = " Tender number: " + it.tenderNo + "\n Tender start: " + it.openDate + "\n Tender close: " + it.closeDate
                saleDate = it.closeDate

            }else{
                Toast.makeText(this,"error", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, AdminPanelActivity::class.java)
                startActivity(intent)
            }
        })

    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.containerTenderss,fragment)
            commit()
        }


}