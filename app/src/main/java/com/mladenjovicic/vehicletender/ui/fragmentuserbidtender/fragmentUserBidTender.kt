package com.mladenjovicic.vehicletender.ui.fragmentuserbidtender

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
import com.mladenjovicic.vehicletender.adapter.BidAdapter

class fragmentUserBidTender : Fragment() {
    lateinit var recyclerViewUserBidList: BidAdapter
    companion object {
        fun newInstance() = fragmentUserBidTender()
    }

    private lateinit var viewModel: UserBidTenderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_user_bid_tender, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val sharedPreferences = requireActivity().getSharedPreferences("UserDate", Context.MODE_PRIVATE)
        var userStatus = sharedPreferences.getInt("status_user", 5)
        viewModel = ViewModelsProviderUtils.getUserBidViewModel(this)
        viewModel.getTenderStock()
        var tenderNo = getActivity()?.intent?.extras?.get("tenderNo") as String
        var statusId = getActivity()?.intent?.extras?.get("statusId") as Int
        viewModel.getTenderFullListID(tenderNo)
        initRecyclerVBidUser(userStatus,statusId)
        //var userId= intent!!.extras?.get("uuid") as String

        viewModel.tenderFullListID?.observe(requireActivity()){
            recyclerViewUserBidList.setTenderActivList(it)
            recyclerViewUserBidList.notifyDataSetChanged()
        }
    }

    private fun initRecyclerVBidUser(userStatus:Int, tenderStatus:Int){
        val recyclerViewListCloseTender= view?.findViewById<RecyclerView>(R.id.recyclerViewUserBId)
        recyclerViewUserBidList = BidAdapter(this,userStatus,tenderStatus)
        recyclerViewListCloseTender?.adapter = recyclerViewUserBidList
    }

}