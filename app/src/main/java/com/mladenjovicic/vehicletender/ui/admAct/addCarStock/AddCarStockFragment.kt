package com.mladenjovicic.vehicletender.ui.admAct.addCarStock

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.mladenjovicic.vehicletender.R
import java.beans.PropertyChangeSupport
import kotlin.properties.Delegates

class AddCarStockFragment : Fragment(), AdapterView.OnItemSelectedListener {
    var carLocation = 0
    var carBrand:Int =0





    //private val changeSupport: PropertyChangeSupport?= PropertyChangeSupport(carBrand)



    companion object {
        fun newInstance() = AddCarStockFragment()
    }

    private lateinit var viewModel: AddCarStockViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_car_stock_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        viewModel = ViewModelProvider(this).get(AddCarStockViewModel::class.java)
        val spinnerCarBrand = view?.findViewById<Spinner>(R.id.spinnerCarBrand)
        val spinnerCarModel= view?.findViewById<Spinner>(R.id.spinnerCarModel)
        val spinnerCarLocation = view?.findViewById<Spinner>(R.id.spinnerCarLocation)
        val editTextCarYear = view?.findViewById<EditText>(R.id.editTextCarYear)
        val editTextCarMileage = view?.findViewById<EditText>(R.id.editTextCarMileage)
        val editTextCarPrice = view?.findViewById<EditText>(R.id.editTextCarPrice)
        val editTextCarReg = view?.findViewById<EditText>(R.id.editTextCarReg)
        val editTextCarComment =view?.findViewById<EditText>(R.id.editTextCarComment)
        val btnAddStockCar = view?.findViewById<Button>(R.id.btnAddStockCar)

        val listLocation = context?.let {
            ArrayAdapter<Any>(it, R.layout.spinner_item)
        }
        viewModel.getListLocation(requireContext())?.observe(viewLifecycleOwner,{ location->
            location?.forEach {
                listLocation?.add(it.city+ ", " + it.zipCode)
            }
        })
        listLocation?.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerCarLocation?.adapter = listLocation
        spinnerCarLocation?.onItemSelectedListener = this

        val listManufacturer = context?.let {
            ArrayAdapter<Any>(it, R.layout.spinner_item)
        }
        viewModel.getListManufacturer(requireContext())?.observe(viewLifecycleOwner,
            { Manufacturer ->
                Manufacturer?.forEach {
                    listManufacturer?.add(it.manufacturer_name)
                }
            })
        listManufacturer?.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerCarBrand?.adapter = listManufacturer
        spinnerCarBrand?.onItemSelectedListener = this

        val listCarModels = context?.let {
            ArrayAdapter<Any>(it, R.layout.spinner_item)
        }

        viewModel.getCarModelsID(requireContext())?.observe(viewLifecycleOwner,{ location->
            location?.forEach {
                println("dev12" + it.model_name)
                listCarModels?.add(it.manufacturer_name+ ", "+it.model_name + " " + it.model_no)

            }
        })
        listCarModels?.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerCarModel?.adapter = listCarModels
        spinnerCarModel?.onItemSelectedListener = this

        btnAddStockCar?.setOnClickListener {
            if(editTextCarYear?.text!!.isNotEmpty() && editTextCarMileage?.text!!.isNotEmpty() && editTextCarPrice?.text!!.isNotEmpty()&&editTextCarReg?.text!!.isNotEmpty()&&editTextCarComment?.text!!.isNotEmpty()){
                viewModel.addCarStock(requireContext(), editTextCarYear?.text.toString().toInt(),carBrand,editTextCarMileage.text.toString().toDouble(),editTextCarPrice.text.toString().toDouble(),editTextCarComment.text.toString(),
                        carLocation,editTextCarReg.text.toString(),true )
                editTextCarYear.text.clear()
                editTextCarMileage.text.clear()
                editTextCarPrice.text.clear()
                editTextCarReg.text.clear()
                editTextCarComment.text.clear()
            }else{
                Toast.makeText(requireContext(), "Sva polja moraju biti popunjena", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id){
            R.id.spinnerCarLocation -> carLocation = position + 1
            R.id.spinnerCarModel -> carBrand = position +1
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}


