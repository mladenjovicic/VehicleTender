package com.mladenjovicic.vehicletender.ui.mainAct.reports

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.ViewModelsProviderUtils

class ReportsFragment:Fragment() {
    companion object {
    fun newInstance() = ReportsFragment()
    }

    private lateinit var viewModel: ReportsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(ReportsViewModel::class.java)
        viewModel = ViewModelsProviderUtils.reprotsUser(this)

        // TODO: Use the ViewModel


    }


}