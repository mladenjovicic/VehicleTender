package com.mladenjovicic.vehicletender.ui.tender.addTenderStock

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.ViewModelsProviderUtils
import com.mladenjovicic.vehicletender.adapter.CarsForSellingAdapter

class AddTenderStockFragment : Fragment() {
    lateinit var recyclerViewCarsForSelling: CarsForSellingAdapter
    companion object {
        fun newInstance() = AddTenderStockFragment()
    }

    private lateinit var viewModel: AddTenderStockViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_tender_stock, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelsProviderUtils.addTenderStock(this)
        var tenderNo = ""
        var statusId = -1
        var saleDate = ""
        var tenderId = -1
        var token = ""
        val sharedPreferences = requireActivity().getSharedPreferences("UserDate", Context.MODE_PRIVATE)
        token =sharedPreferences.getString("token", "null").toString()
        tenderNo= getActivity()?.intent?.extras?.get("tenderNo") as String
        tenderId = getActivity()?.intent?.extras?.get("tenderId") as Int
        statusId = getActivity()?.intent?.extras?.get("statusId") as Int
        saleDate = getActivity()?.intent?.extras?.get("saleDate") as String
        viewModel.getTenderBYTenderNo( tenderNo)
        viewModel.getStockCarList( false)
        viewModel.getStockCarUpdate(false)
        initRecyclerViewCarsForSelling(token)
        viewModel.stockCarUpdate?.observe(requireActivity(), Observer {

            recyclerViewCarsForSelling.setCarForSellingList(it)
            recyclerViewCarsForSelling.saleDate= saleDate
            recyclerViewCarsForSelling.tenderId = tenderNo
            recyclerViewCarsForSelling.tenderIdServer = tenderId
            recyclerViewCarsForSelling.notifyDataSetChanged()
        })
    }
    private fun initRecyclerViewCarsForSelling(token:String){
        val recyclerViewRecyclerViewCarsForSelling = view?.findViewById<RecyclerView>(R.id.recyclerViewrecyclerViewCarsForSelling)
        recyclerViewCarsForSelling = CarsForSellingAdapter(requireActivity(), viewModel, token, this)
        recyclerViewRecyclerViewCarsForSelling?.adapter = recyclerViewCarsForSelling
    }

}