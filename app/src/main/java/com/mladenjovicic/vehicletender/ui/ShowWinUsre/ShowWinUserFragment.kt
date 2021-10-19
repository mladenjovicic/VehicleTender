package com.mladenjovicic.vehicletender.ui.ShowWinUsre

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.ViewModelsProviderUtils
import com.mladenjovicic.vehicletender.adapter.ShowWinUserAdapter

class ShowWinUserFragment : Fragment() {
    lateinit var recyclerViewWinBidList: ShowWinUserAdapter
    companion object {
        fun newInstance() = ShowWinUserFragment()
    }

    private lateinit var viewModel: ShowWinUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_show_win_user, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelsProviderUtils.showWinUser(this)
        var tenderId = getActivity()?.intent?.extras?.get("tenderId") as Int
        viewModel.bidUserWin(true,tenderId )

        initRecyclerWinBidList()

        viewModel.tenderWiningList?.observe(viewLifecycleOwner) {

            recyclerViewWinBidList.setTenderWiningList(it)
            recyclerViewWinBidList.notifyDataSetChanged()
        }


    }

    private fun initRecyclerWinBidList(){
        val recyclerViewWinBinUser= view?.findViewById<RecyclerView>(R.id.recyclerViewListWinners)
        recyclerViewWinBidList = ShowWinUserAdapter(this)
        recyclerViewWinBinUser?.adapter = recyclerViewWinBidList
    }

}