package com.mladenjovicic.vehicletender.ui.admAct.ListUser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.ViewModelsProviderUtils
import com.mladenjovicic.vehicletender.adapter.UsersListAdapter

class ListUserFragment : Fragment() {

    lateinit var recyclerViewUsersList:UsersListAdapter

    companion object {
        fun newInstance() = ListUserFragment()
    }

    private lateinit var viewModel: ListUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_user, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelsProviderUtils.get(this)
      //  viewModel = ViewModelProvider(this).get(ListUserViewModel::class.java)
        viewModel = ViewModelsProviderUtils.getListUserViewModel(this)
        viewModel.getUsersList(requireContext())
        initRecyclerViewAllUser()
        viewModel.listAllUser?.observe(requireActivity()){

            recyclerViewUsersList.setUsersList(it)
            recyclerViewUsersList.notifyDataSetChanged()
        }



    }
    private fun initRecyclerViewAllUser(){
        val recyclerViewListUsers = view?.findViewById<RecyclerView>(R.id.recyclerViewListUsers)
        recyclerViewUsersList = UsersListAdapter(this)
        recyclerViewListUsers?.adapter = recyclerViewUsersList
    }
}