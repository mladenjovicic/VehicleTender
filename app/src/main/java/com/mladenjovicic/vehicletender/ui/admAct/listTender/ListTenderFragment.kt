package com.mladenjovicic.vehicletender.ui.admAct.listTender

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.adapter.CarsStockAdapter
import com.mladenjovicic.vehicletender.adapter.TenderActivAdapter

class ListTenderFragment : Fragment() {
    lateinit var recyclerViewTenderActivList: TenderActivAdapter
    lateinit var recyclerViewTenderCloseList: TenderActivAdapter

    companion object {
        fun newInstance() = ListTenderFragment()
    }

    private lateinit var viewModel: ListTenderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_tender_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListTenderViewModel::class.java)
        val recyclerViewListActivesTender= view?.findViewById<RecyclerView>(R.id.recyclerViewListActivesTender)
        val recyclerViewListCloseTender= view?.findViewById<RecyclerView>(R.id.recyclerViewListCloseTender)
        val buttonShowActivTender = view?.findViewById<Button>(R.id.buttonShowActivTender)
        val buttonShowCloseTender = view?.findViewById<Button>(R.id.buttonShowCloseTender)

        recyclerViewListActivesTender?.visibility = View.VISIBLE
        recyclerViewListCloseTender?.visibility = View.GONE
        viewModel.getActivesTenderList(requireContext(), 1)
        initRecyclerVTenderActivList()

        viewModel.tenderModel?.observe(requireActivity()){
            if (it==null){
                recyclerViewListActivesTender?.visibility = View.GONE
            }else{
                recyclerViewTenderActivList.setTenderActivList(it)
                recyclerViewTenderActivList.notifyDataSetChanged()
            }
        }


        buttonShowActivTender?.setOnClickListener {
            recyclerViewListActivesTender?.visibility = View.VISIBLE
            recyclerViewListCloseTender?.visibility = View.GONE
            viewModel.getActivesTenderList(requireContext(), 1)
            initRecyclerVTenderActivList()

            viewModel.tenderModel?.observe(requireActivity()){
                if (it==null){
                    recyclerViewListActivesTender?.visibility = View.GONE
                }else{
                    recyclerViewTenderActivList.setTenderActivList(it)
                    recyclerViewTenderActivList.notifyDataSetChanged()
                }
            }
        }
        buttonShowCloseTender?.setOnClickListener {
            recyclerViewListActivesTender?.visibility = View.GONE
            recyclerViewListCloseTender?.visibility = View.VISIBLE

            viewModel.getActivesTenderList(requireContext(), 2)
            initRecyclerVTenderCloseList()
            viewModel.tenderModel?.observe(requireActivity()){
                if (it==null){
                    recyclerViewListCloseTender?.visibility = View.GONE
                }else{
                    recyclerViewTenderCloseList.setTenderActivList(it)
                    recyclerViewTenderCloseList.notifyDataSetChanged()
                }
            }
        }



    }

    private fun initRecyclerVTenderActivList(){
        val recyclerViewListActivesTender= view?.findViewById<RecyclerView>(R.id.recyclerViewListActivesTender)
        recyclerViewTenderActivList = TenderActivAdapter(this)
        recyclerViewListActivesTender?.adapter = recyclerViewTenderActivList
    }

    private fun initRecyclerVTenderCloseList(){
        val recyclerViewListCloseTender= view?.findViewById<RecyclerView>(R.id.recyclerViewListCloseTender)
        recyclerViewTenderCloseList = TenderActivAdapter(this)
        recyclerViewListCloseTender?.adapter = recyclerViewTenderCloseList
    }

}