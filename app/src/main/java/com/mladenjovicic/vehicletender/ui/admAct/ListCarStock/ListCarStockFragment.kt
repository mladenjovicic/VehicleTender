package com.mladenjovicic.vehicletender.ui.admAct.ListCarStock

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.adapter.CarsStockAdapter
import com.mladenjovicic.vehicletender.adapter.UsersListAdapter

class ListCarStockFragment : Fragment() {
    lateinit var recyclerViewCatStockList:CarsStockAdapter
    companion object {
        fun newInstance() = ListCarStockFragment()
    }

    private lateinit var viewModel: ListCarStockViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_car_stock_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListCarStockViewModel::class.java)
        /*viewModel.getListCarStock(requireContext())
        initRecyclerViewAllUser()
        viewModel.listCarStock?.observe(requireActivity()){
            recyclerViewCatStockList.setCarCtockList(it)
            recyclerViewCatStockList.notifyDataSetChanged()
        }*/

        viewModel.getStockCarList(requireContext())
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