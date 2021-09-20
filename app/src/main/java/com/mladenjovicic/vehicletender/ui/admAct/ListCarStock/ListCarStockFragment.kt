package com.mladenjovicic.vehicletender.ui.admAct.ListCarStock

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.ViewModelsProviderUtils
import com.mladenjovicic.vehicletender.adapter.CarsStockAdapter

class ListCarStockFragment : Fragment() {
    lateinit var recyclerViewCatStockList:CarsStockAdapter
    companion object {
        fun newInstance() = ListCarStockFragment()
    }

    private lateinit var viewModel: ListCarStockViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_car_stock, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelsProviderUtils.getListCarStock(this)

        viewModel.getStockCarList()
        initRecyclerViewAllUser()
        viewModel.stockCarList?.observe(requireActivity()){
            recyclerViewCatStockList.setCarCtockList(it)
            recyclerViewCatStockList.notifyDataSetChanged()
        }
    }

    private fun initRecyclerViewAllUser(){
        val recyclerViewListUsers = view?.findViewById<RecyclerView>(R.id.recyclerViewCarStock)
        recyclerViewCatStockList = CarsStockAdapter(this)
        recyclerViewListUsers?.adapter = recyclerViewCatStockList
    }

}