package com.mladenjovicic.vehicletender.ui.admAct.addLocation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.ViewModelsProviderUtils
import com.mladenjovicic.vehicletender.data.model.api.LocationModelAPI

class AddLocationFragment : Fragment() {

    companion object {
        fun newInstance() = AddLocationFragment()
    }

    private lateinit var viewModel: AddLocationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_location, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val editTextNameNewLocation = view?.findViewById<EditText>(R.id.editTextNameNewLocation)
        val editTextSaveZipCodeNewLocation = view?.findViewById<EditText>(R.id.editTextSaveZipCodeNewLocation)
        val btnSaveNewLocation = view?.findViewById<Button>(R.id.btnSaveNewLocation)

        viewModel = ViewModelsProviderUtils.addLocation(this)


        btnSaveNewLocation?.setOnClickListener {



            if (editTextNameNewLocation?.text!!.isNotEmpty()&& editTextSaveZipCodeNewLocation?.text!!.isNotEmpty()){
                addLocationJSON(editTextNameNewLocation?.text.toString(),editTextSaveZipCodeNewLocation?.text.toString())
            }else{
                Toast.makeText(requireContext(), "Polja moraju biti popunjena ", Toast.LENGTH_SHORT).show()
            }

        }


    }
    fun addLocationJSON(city:String, zipCode:String){
        val sharedPreferences = requireActivity().getSharedPreferences("UserDate", Context.MODE_PRIVATE)
        var token =sharedPreferences.getString("token", "null")

        viewModel.addLocationJSON(token!!,null ,city, zipCode)
        val editTextNameNewLocation = view?.findViewById<EditText>(R.id.editTextNameNewLocation)
        val editTextSaveZipCodeNewLocation = view?.findViewById<EditText>(R.id.editTextSaveZipCodeNewLocation)

        viewModel.requestState.observe(requireActivity()) {
            if(it.pending)
                Log.e("Loading", "retrofit request is in progress, show loading spinner")

            if(it.successful){
                Log.e("Success", "retrofit request is successful")
                var count = 0
                viewModel.getNewLocationObserver().observe(requireActivity(), Observer<LocationModelAPI?>{

                    if(it!=null){
                        Toast.makeText(requireContext(), "Request is successful", Toast.LENGTH_SHORT).show()
                        viewModel.addNewLocation(it.Id!!, it.City.toString(), it.ZipCode.toString())
                        editTextNameNewLocation!!.text.clear()
                        editTextSaveZipCodeNewLocation!!.text.clear()
                        viewModel.createNewLocation.postValue(null)
                    }
                })
            }
            else{
                Toast.makeText(requireContext(), it.errorMessage.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

}