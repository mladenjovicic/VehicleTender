package com.mladenjovicic.vehicletender.ui.tender.AddTenderUser

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.ViewModelsProviderUtils
import com.mladenjovicic.vehicletender.adapter.UserTenderListAdapter

class AddTenderUserFragment : Fragment() {
    lateinit var recyclerViewUserTenderList: UserTenderListAdapter
    companion object {
        fun newInstance() = AddTenderUserFragment()
    }

    private lateinit var viewModel: AddTenderUserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_tender_user, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelsProviderUtils.addTenderUser(this)
        viewModel.readUser("0")
        val sharedPreferences = requireActivity().getSharedPreferences("UserDate", Context.MODE_PRIVATE)
        var token =sharedPreferences.getString("token", "null").toString()
        var userId = sharedPreferences.getString("uuidUser","null").toString()
        var tenderNo= getActivity()?.intent?.extras?.get("tenderNo") as String
        var tenderId = getActivity()?.intent?.extras?.get("tenderId") as Int
        var statusId = getActivity()?.intent?.extras?.get("statusId") as Int
        initRecyclerViewCarsForSelling("")
        viewModel.userUpdate?.observe(requireActivity(), Observer {
            recyclerViewUserTenderList.setUserTenderList(it)
            recyclerViewUserTenderList.token  = token
            recyclerViewUserTenderList.tenderId = tenderId
            recyclerViewUserTenderList.userId =  userId
        })
    }

    private fun initRecyclerViewCarsForSelling(token:String){
        val recyclerViewUserTender = view?.findViewById<RecyclerView>(R.id.recyclerViewUserTender)
        recyclerViewUserTenderList = UserTenderListAdapter(requireActivity(), viewModel, "", this)
        recyclerViewUserTender?.adapter = recyclerViewUserTenderList
    }



}