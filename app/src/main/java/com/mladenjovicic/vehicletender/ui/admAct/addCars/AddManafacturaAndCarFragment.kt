package com.mladenjovicic.vehicletender.ui.admAct.addCars

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.mladenjovicic.vehicletender.R

class AddManafacturaAndCarFragment : Fragment(), AdapterView.OnItemSelectedListener {

    var idMan = 0

    companion object {
        fun newInstance() = AddManafacturaAndCarFragment()
    }

    private lateinit var viewModel: AddManafacturaAndCarViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.add_manafactura_and_car_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddManafacturaAndCarViewModel::class.java)

        addNewMan()
        addNewCar()
    }
    fun addNewMan(){
        val btnAddNewManufacturer = view?.findViewById<Button>(R.id.btnAddNewManufacturer)
        val editTextAddNewManufacturer = view?.findViewById<EditText>(R.id.editTextAddNewManufacturer)
        val spinnerAddManufacturer = view?.findViewById<Spinner>(R.id.spinnerAddManufacturer)
        viewModel = ViewModelProvider(this).get(AddManafacturaAndCarViewModel::class.java)
        btnAddNewManufacturer?.setOnClickListener {
            viewModel.addManufacturer(requireContext(), editTextAddNewManufacturer?.text.toString())
            editTextAddNewManufacturer?.text?.clear()
        }
    }

    fun addNewCar(){
        viewModel = ViewModelProvider(this).get(AddManafacturaAndCarViewModel::class.java)
        val spinnerAddManufacturer = view?.findViewById<Spinner>(R.id.spinnerAddManufacturer)
        val btnAddCar = view?.findViewById<Button>(R.id.btnAddCar)
        val editTextAddModelName = view?.findViewById<EditText>(R.id.editTextAddModelName)
        val editTextModelNumber = view?.findViewById<EditText>(R.id.editTextAddModelNumber)

        val listMan = context?.let {
            ArrayAdapter<Any>(it, R.layout.spinner_item)
        }


        viewModel.getListManafactura(requireContext())?.observe(viewLifecycleOwner,{ location->
            location?.forEach {
                listMan?.add(it.manufacturer_name)
            }
        })
        listMan?.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerAddManufacturer?.adapter = listMan
        spinnerAddManufacturer?.onItemSelectedListener = this
        btnAddCar?.setOnClickListener {
            viewModel.addCar(requireContext(),5,editTextAddModelName?.text.toString(),editTextModelNumber?.text.toString(), idMan )
            editTextAddModelName?.text?.clear()
            editTextModelNumber?.text?.clear()
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