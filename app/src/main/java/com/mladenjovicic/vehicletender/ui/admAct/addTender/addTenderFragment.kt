package com.mladenjovicic.vehicletender.ui.admAct.addTender

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mladenjovicic.vehicletender.R

class addTenderFragment : Fragment() {

    companion object {
        fun newInstance() = addTenderFragment()
    }

    private lateinit var viewModel: AddTenderViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.add_tender_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddTenderViewModel::class.java)
        // TODO: Use the ViewModel
    }

}