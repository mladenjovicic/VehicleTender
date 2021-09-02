package com.mladenjovicic.vehicletender.ui.mainAct.sec

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mladenjovicic.vehicletender.R

class SecFragment : Fragment() {

    companion object {
        fun newInstance() = SecFragment()
    }

    private lateinit var viewModel: SecViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sec_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SecViewModel::class.java)
        // TODO: Use the ViewModel
    }

}