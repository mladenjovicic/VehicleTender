package com.mladenjovicic.vehicletender.ui.admAct.listTender

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mladenjovicic.vehicletender.R

class ListTenderFragment : Fragment() {

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
        // TODO: Use the ViewModel
    }

}