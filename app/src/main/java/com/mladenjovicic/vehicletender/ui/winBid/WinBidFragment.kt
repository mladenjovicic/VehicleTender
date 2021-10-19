package com.mladenjovicic.vehicletender.ui.winBid

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.ViewModelsProviderUtils
import com.mladenjovicic.vehicletender.adapter.BidWinAdapter
import com.mladenjovicic.vehicletender.adapter.CarsStockAdapter

class WinBidFragment : Fragment() {

    companion object {
        fun newInstance() = WinBidFragment()
    }

    private lateinit var viewModel: WinBidViewModel
    lateinit var recyclerViewBidWinList:BidWinAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_win_bid, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelsProviderUtils.getBidWinViewModel(this)
        var userLocationId= requireActivity()?.intent!!.extras?.get("stockId") as Int
        var carId = requireActivity()?.intent!!.extras?.get("carId") as Int
        val sharedPreferences = requireActivity().getSharedPreferences("UserDate", Context.MODE_PRIVATE)
        var token =sharedPreferences.getString("token", "null").toString()

        viewModel.getBidListStockId(userLocationId.toInt())
        initRecyclerBidWinList()
        viewModel.bidModelDB?.observe(requireActivity()){
            recyclerViewBidWinList.setBidActiveList(it)
            recyclerViewBidWinList.notifyDataSetChanged()
            recyclerViewBidWinList.token = token
            recyclerViewBidWinList.carId = carId
        }
    }

    private fun initRecyclerBidWinList(){
        val recyclerViewListUsers = view?.findViewById<RecyclerView>(R.id.recyclerVIewWinBid)
        recyclerViewBidWinList = BidWinAdapter(this, viewModel, this)
        recyclerViewListUsers?.adapter = recyclerViewBidWinList
    }
}