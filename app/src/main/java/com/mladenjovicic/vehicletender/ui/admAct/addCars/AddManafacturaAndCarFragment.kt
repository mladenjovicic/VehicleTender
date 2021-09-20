package com.mladenjovicic.vehicletender.ui.admAct.addCars

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.ViewModelsProviderUtils
import com.mladenjovicic.vehicletender.data.model.api.CarModelApi
import com.mladenjovicic.vehicletender.data.model.api.ManufacturerModelAPI

class AddManafacturaAndCarFragment : Fragment(), AdapterView.OnItemSelectedListener {

    var idMan = 0

    companion object {
        fun newInstance() = AddManafacturaAndCarFragment()
    }

    private lateinit var viewModel: AddManafacturaAndCarViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_manafactura_and_car, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(AddManafacturaAndCarViewModel::class.java)
        viewModel = ViewModelsProviderUtils.AddManafacturaAndCar(this)

        addNewMan()
        addNewCar()
    }
    fun addNewMan(){
        val btnAddNewManufacturer = view?.findViewById<Button>(R.id.btnAddNewManufacturer)
        val editTextAddNewManufacturer = view?.findViewById<EditText>(R.id.editTextAddNewManufacturer)
        val spinnerAddManufacturer = view?.findViewById<Spinner>(R.id.spinnerAddManufacturer)
        btnAddNewManufacturer?.setOnClickListener {
           if(editTextAddNewManufacturer?.text!!.isNotEmpty()){
               viewModel.addManufacturerJSON(999, editTextAddNewManufacturer.text.toString())
               viewModel.getNewManufacturerObserver().observe(requireActivity(), Observer<ManufacturerModelAPI?>{
                   if(it.ManufacturerName!="null"){
                       Toast.makeText(requireContext(), "Request is successful", Toast.LENGTH_SHORT).show()
                       viewModel.addManufacturer(it.ID!!, it.ManufacturerName!!)
                       editTextAddNewManufacturer?.text?.clear()
                   }else{
                       Toast.makeText(requireContext(), "Request is error", Toast.LENGTH_SHORT).show()
                   }
               })
           }else{
               Toast.makeText(requireContext(), "Polje mora biti popunjeno", Toast.LENGTH_SHORT).show()
           }
            viewModel.requestState.observe(requireActivity()) {
                if(it.pending)
                    Log.e("Loading", "retrofit request is in progress, show loading spinner")

                if(it.successful)
                    Log.e("Success", "retrofit request is successful")

            }}
    }

    fun addNewCar(){

        val spinnerAddManufacturer = view?.findViewById<Spinner>(R.id.spinnerAddManufacturer)
        val btnAddCar = view?.findViewById<Button>(R.id.btnAddCar)
        val editTextAddModelName = view?.findViewById<EditText>(R.id.editTextAddModelName)
        val editTextModelNumber = view?.findViewById<EditText>(R.id.editTextAddModelNumber)

        val listMan = context?.let {
            ArrayAdapter<Any>(it, R.layout.spinner_item)
        }


        viewModel.getListManafactura()?.observe(viewLifecycleOwner,{ location->
            location?.forEach {
                listMan?.add(it.manufacturer_name)
            }
        })
        listMan?.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerAddManufacturer?.adapter = listMan
        spinnerAddManufacturer?.onItemSelectedListener = this
        btnAddCar?.setOnClickListener {

            if(editTextAddModelName?.text!!.isNotEmpty()&&editTextModelNumber?.text!!.isNotEmpty()&&idMan>0){
                viewModel.addCarModelJSON(999, editTextAddModelName.text.toString(), editTextModelNumber.text.toString(), idMan)

                viewModel.getNewCarModelObserver().observe(requireActivity(), Observer<CarModelApi?>{
                    if(it.ModelName != "null"){
                        Toast.makeText(requireContext(), "Request is successful", Toast.LENGTH_SHORT).show()
                        viewModel.addCarModel(it.ID!!,it.ModelName!!,it.ModelName!!, it.ManufacturerId!!)
                        editTextAddModelName?.text?.clear()
                        editTextModelNumber?.text?.clear()
                    }else{
                        Toast.makeText(requireContext(), "Request is error", Toast.LENGTH_SHORT).show()
                    }
                })
                viewModel.requestState.observe(requireActivity()) {
                    if(it.pending)
                        Log.e("Loading", "retrofit request is in progress, show loading spinner")

                    if(it.successful)
                        Log.e("Success", "retrofit request is successful")

                }
            }else{
                Toast.makeText(requireContext(),"Sva polja moraju biti popunjena", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        when(parent?.id){
            R.id.spinnerAddManufacturer -> idMan = pos+1
        }
    }
    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}