package com.mladenjovicic.vehicletender

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.vehicletender.adapter.CarsForSellingAdapter
import com.mladenjovicic.vehicletender.adapter.CarsStockAdapter
import com.mladenjovicic.vehicletender.ui.tender.TenderUseViewModel

class TenderUseActivity2 : AppCompatActivity() {
    private lateinit var viewModel: TenderUseViewModel
    lateinit var recyclerViewCarsForSelling: CarsForSellingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tender_use2)
        //viewModel = ViewModelProvider(this).get(TenderUseViewModel::class.java)
        viewModel = ViewModelsProviderUtils.getTenderViewModel(this)
        val textViewTest123 = findViewById<TextView>(R.id.textViewTest123)
        var tenderNo = ""
        var statusId = -1
        var saleDate = ""

        tenderNo= intent!!.extras?.get("tenderNo") as String
        statusId = intent!!.extras?.get("statusId") as Int

        viewModel.getTenderBYTenderNo( tenderNo)
        viewModel.getStockCarList( false)
        viewModel.getStockCarUpdate(false)

        initRecyclerViewCarsForSelling()

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

        viewModel.stockCarUpdate?.observe(this, Observer {
            recyclerViewCarsForSelling.setCarForSellingList(it)
            recyclerViewCarsForSelling.saleDate= saleDate
            recyclerViewCarsForSelling.tenderId = tenderNo
            recyclerViewCarsForSelling.notifyDataSetChanged()
        })
    }

    private fun initRecyclerViewCarsForSelling(){
        val recyclerViewRecyclerViewCarsForSelling = findViewById<RecyclerView>(R.id.recyclerViewrecyclerViewCarsForSelling)
        recyclerViewCarsForSelling = CarsForSellingAdapter(this, viewModel)
        recyclerViewRecyclerViewCarsForSelling?.adapter = recyclerViewCarsForSelling
    }

}