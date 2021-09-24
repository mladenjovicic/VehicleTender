package com.mladenjovicic.vehicletender.ui.admAct.addTender

import android.app.DatePickerDialog
import android.content.Context
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
import com.mladenjovicic.vehicletender.data.model.api.TenderModelAPI
import java.text.SimpleDateFormat
import java.util.*

class addTenderFragment : Fragment(), AdapterView.OnItemSelectedListener {
    var tenderStatus = -1

    companion object {
        fun newInstance() = addTenderFragment()
    }

    private lateinit var viewModel: AddTenderViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_tender, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelsProviderUtils.addTender(this)
        addNewTender()



    }

    fun addNewTender(){
        val editTextDateOpenDate = view?.findViewById<EditText>(R.id.editTextDateOpenDate)
        val editTextDateCloseDate = view?.findViewById<EditText>(R.id.editTextDateCloseDate)
        val spinnerTenderStatus = view?.findViewById<Spinner>(R.id.spinnerTenderStatus)
        val btnAddNewTender = view?.findViewById<Button>(R.id.btnAddNewTender)
        val sharedPreferences = requireActivity().getSharedPreferences("UserDate", Context.MODE_PRIVATE)
        val CalendarOpenDate = Calendar.getInstance()

        val datePickerOnDataSetListenerOpenDate = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            CalendarOpenDate.set(Calendar.YEAR, year)
            CalendarOpenDate.set(Calendar.MONTH, monthOfYear)
            CalendarOpenDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel(CalendarOpenDate, editTextDateOpenDate!!)
        }
        val datePickerOnDataSetListenerCloseDate = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            CalendarOpenDate.set(Calendar.YEAR, year)
            CalendarOpenDate.set(Calendar.MONTH, monthOfYear)
            CalendarOpenDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel(CalendarOpenDate, editTextDateCloseDate!!)
        }
        editTextDateOpenDate?.setOnClickListener {
            DatePickerDialog(requireContext(),datePickerOnDataSetListenerOpenDate, CalendarOpenDate
                .get(Calendar.YEAR), CalendarOpenDate.get(Calendar.MONTH),
                CalendarOpenDate.get(Calendar.DAY_OF_MONTH)).show()
        }
        editTextDateCloseDate?.setOnClickListener {
            DatePickerDialog(requireContext(),datePickerOnDataSetListenerCloseDate, CalendarOpenDate
                .get(Calendar.YEAR), CalendarOpenDate.get(Calendar.MONTH),
                CalendarOpenDate.get(Calendar.DAY_OF_MONTH)).show()
        }
        val listStatus = context?.let {
            ArrayAdapter<Any>(it, R.layout.spinner_item)
        }

        viewModel.getListStatus()?.observe(viewLifecycleOwner,{ status->
            status?.forEach {
                listStatus?.add(it.statusType)
            }
        })
        listStatus?.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerTenderStatus?.adapter = listStatus
        spinnerTenderStatus?.onItemSelectedListener = this
        btnAddNewTender?.setOnClickListener {
            if (editTextDateOpenDate?.text!!.isNotEmpty()&&editTextDateCloseDate?.text!!.isNotEmpty()){
                val rnds = (0..9999).random()
                viewModel.addTenderJSON(rnds,System.currentTimeMillis().toString(),sharedPreferences.getString("uuidUser", "null").toString(),UUID.randomUUID().toString(), editTextDateOpenDate.text.toString(), editTextDateCloseDate.text.toString(), tenderStatus )


                viewModel.requestState.observe(requireActivity()) {
                    if(it.pending)
                        Log.e("Loading", "retrofit request is in progress, show loading spinner")

                    if(it.successful){
                        Log.e("Success", "retrofit request is successful")

                        viewModel.getNewTenderObserver().observe(requireActivity(), Observer<TenderModelAPI?>{
                            if (it !=null){
                                Toast.makeText(requireContext(), "Request is successful", Toast.LENGTH_SHORT).show()
                                viewModel.addTender(it.id!!, it.createdDate!!, it.createdBy!!, it.tenderNo!!, it.openDate!!, it.closeDate!!, it.statusId!!)
                                editTextDateOpenDate.text.clear()
                                editTextDateCloseDate.text.clear()
                                viewModel.createNewTender.postValue(null)
                            }
                        })
                    }
                }
            }else{
                Toast.makeText(requireContext(), "Sva polja moja biti popunjena", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun updateLabel(myCalendar: Calendar, dateEditText: EditText) {
        val myFormat: String = "YYYY-MM-dd"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        dateEditText.setText(sdf.format(myCalendar.time))
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id){
            R.id.spinnerTenderStatus ->  getStatusId(position)
        }
    }

    fun getStatusId(pos:Int){
        viewModel.getListStatus()?.observe(viewLifecycleOwner,{ status->
            status?.forEach {
                tenderStatus = status[pos].idServer!!
            }
        })

    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}