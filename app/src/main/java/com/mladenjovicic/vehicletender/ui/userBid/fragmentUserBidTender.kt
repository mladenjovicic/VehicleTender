package com.mladenjovicic.vehicletender.ui.userBid

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
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
        var uuidUser = sharedPreferences.getString("uuidUser", "")
        var statusUser =sharedPreferences.getInt("status_user", 0)
        var token =sharedPreferences.getString("token", "null").toString()
        val btnCloseTender = view?.findViewById<Button>(R.id.btnCloseTender)
        viewModel = ViewModelsProviderUtils.getUserBidViewModel(this)
        viewModel.getTenderStock()
        var tenderNo = getActivity()?.intent?.extras?.get("tenderNo") as String
        var statusId = getActivity()?.intent?.extras?.get("statusId") as Int
        var tenderId = getActivity()?.intent?.extras?.get("tenderId") as Int

        if(statusId == 1){
        if(statusUser ==2){
            btnCloseTender?.visibility = View.VISIBLE
        }else{
            btnCloseTender?.visibility = View.GONE
        }}else{
            btnCloseTender?.visibility = View.GONE
        }
        
        btnCloseTender?.setOnClickListener {

            viewModel.getTenderModelId(tenderId)?.observe(requireActivity(), Observer {
                if(it!=null){
                    viewModel.updateTenderJSON(token,tenderId,it.createdDate, it.createdBy,it.tenderNo,it.openDate,it.closeDate,2)
                }
            })
            viewModel.requestState.observe(requireActivity()) {
                if(it.pending)
                    Log.e("Loading", "retrofit request is in progress, show loading spinner")
                if(it.successful){
                    Log.e("Success", "retrofit request is successful")
                    viewModel.updateTender.observe(requireActivity()){
                        if(it!=null){
                            viewModel.updateTenderDB(it.id!!, it.createdDate!!, it.createdBy!!, it.tenderNo!!, it.openDate!!, it.closeDate!!, it.statusId!!)
                        }
                    }
                }
            }
        }

        viewModel.getTenderFullListID(tenderId.toString())
        initRecyclerVBidUser(userStatus,statusUser, uuidUser!!, token)

        viewModel.tenderFullListID?.observe(requireActivity()){
            if(it.isNotEmpty()){
            recyclerViewUserBidList.setTenderActivList(it)
            recyclerViewUserBidList.notifyDataSetChanged()}
        }
    }

    private fun initRecyclerVBidUser(userStatus:Int, tenderStatus:Int, uuidUser:String, token:String){
        val recyclerViewListCloseTender= view?.findViewById<RecyclerView>(R.id.recyclerViewUserBId)
        recyclerViewUserBidList = BidAdapter(this,userStatus,tenderStatus, viewModel, uuidUser,requireActivity(), token)
        recyclerViewListCloseTender?.adapter = recyclerViewUserBidList
    }

}