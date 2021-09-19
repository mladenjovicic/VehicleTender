package com.mladenjovicic.vehicletender.ui.admAct.addLocation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.ViewModelsProviderUtils

class AddLocationFragment : Fragment() {

    companion object {
        fun newInstance() = AddLocationFragment()
    }

    private lateinit var viewModel: AddLocationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_location_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val editTextNameNewLocation = view?.findViewById<EditText>(R.id.editTextNameNewLocation)
        val editTextSaveZipCodeNewLocation = view?.findViewById<EditText>(R.id.editTextSaveZipCodeNewLocation)
        val btnSaveNewLocation = view?.findViewById<Button>(R.id.btnSaveNewLocation)

        //viewModel = ViewModelProvider(this).get(AddLocationViewModel::class.java)
        viewModel = ViewModelsProviderUtils.addLocation(this)


        btnSaveNewLocation?.setOnClickListener {
            viewModel.addLocationJSON(5,editTextNameNewLocation?.text.toString(),editTextSaveZipCodeNewLocation?.text.toString())
            viewModel.addNewLocation(editTextNameNewLocation?.text.toString(),editTextSaveZipCodeNewLocation?.text.toString())
            editTextNameNewLocation?.text?.clear()
            editTextSaveZipCodeNewLocation?.text?.clear()

        }


    }

}