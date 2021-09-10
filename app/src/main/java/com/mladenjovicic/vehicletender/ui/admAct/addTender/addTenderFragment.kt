package com.mladenjovicic.vehicletender.ui.admAct.addTender

import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.mladenjovicic.vehicletender.R
import java.text.SimpleDateFormat
import java.util.*

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
        val editTextDateOpenDate = view?.findViewById<EditText>(R.id.editTextDateOpenDate)
        val editTextDateCloseDate = view?.findViewById<EditText>(R.id.editTextDateCloseDate)
        val spinnerTenderStatus = view?.findViewById<Spinner>(R.id.spinnerTenderStatus)
        val btnAddNewTender = view?.findViewById<Button>(R.id.btnAddNewTender)

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
        btnAddNewTender?.setOnClickListener {
            if (editTextDateOpenDate?.text!!.isNotEmpty()&&editTextDateCloseDate?.text!!.isNotEmpty()){

            }else{
                Toast.makeText(requireContext(), "Sva polja moja biti popunjena", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun updateLabel(myCalendar: Calendar, dateEditText: EditText) {
        val myFormat: String = "dd-MMM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        dateEditText.setText(sdf.format(myCalendar.time))
    }

}