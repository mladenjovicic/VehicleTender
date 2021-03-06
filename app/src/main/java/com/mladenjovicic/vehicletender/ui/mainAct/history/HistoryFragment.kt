package com.mladenjovicic.vehicletender.ui.mainAct.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.ViewModelsProviderUtils
import com.mladenjovicic.vehicletender.adapter.UserTenderAdapter

class HistoryFragment : Fragment() {
    lateinit var recyclerViewTenderActivList: UserTenderAdapter
    companion object {
        fun newInstance() = HistoryFragment()
    }

    private lateinit var viewModel: SecViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_history, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelsProviderUtils.historyUser(this)
        viewModel.getActivesTenderList(2)
        initRecyclerVTenderActivList()

        viewModel.tenderModel?.observe(requireActivity()){
            recyclerViewTenderActivList.setTenderActivList(it)
            recyclerViewTenderActivList.notifyDataSetChanged()
        }

    }
    private fun initRecyclerVTenderActivList(){
        val recyclerViewListActivesTender= view?.findViewById<RecyclerView>(R.id.recyclerViewUserHistory)
        recyclerViewTenderActivList = UserTenderAdapter(this)
        recyclerViewListActivesTender?.adapter = recyclerViewTenderActivList
    }

}