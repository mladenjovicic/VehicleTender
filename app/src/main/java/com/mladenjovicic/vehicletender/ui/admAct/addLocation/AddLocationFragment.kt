package com.mladenjovicic.vehicletender.ui.admAct.addLocation

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

        viewModel.addLocationJSON("bearer Ptm4EQV113pVRiSKX1R4FbyQ2zIvCUoVvJlS_tY8ijH4w739m_hSwgI3N23Z5Na8USQpJOA5bUpCkrUh8uqlaavTZvfq6WhDvKZ5fR9LYQruQwrCzvnNS9ryl_RFmxdYbDhW-ZhNRUiqqCRFuwNNUwqZPz2TRbOi5EsGR2UwFA4Wmmua9U7BaNscG78eDmFSqAeWGuGGArDNZzbff8H3C94yEULh8fVmy6dajbhn0SCAx1AzA1VeZqCS_a6SWCdfvNQwl82PNJgr0O3anDmI2T3rnw-ss_ahSI-awTHNmYfYAGYFdP7Ne29ZjLXaCMhZ81KtlwVXybmHK58M16BqLixScogF4A9aMMZG5exh0Gvr-1NYPr3zxGdTVbJXIUxE6GynAc5LJzY0Zihhmzv9Y6rOkoBk4lUTr1BRM4HATWuxNPMoQdbrJ5anUGrVVLRjdkwGE_CUh3ymvhyvqXszDtuwXMWTLon_-XfpufcsoD2SDGCgwWW5dwUpswSwE7DAn97EgZ2nSNU8OAaGZKBCpQ",null ,city, zipCode)
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