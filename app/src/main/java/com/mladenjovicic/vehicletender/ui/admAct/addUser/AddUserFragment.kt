package com.mladenjovicic.vehicletender.ui.admAct.addUser

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.mladenjovicic.vehicletender.R
import java.util.*

class AddUserFragment : Fragment(), AdapterView.OnItemSelectedListener  {
    var statusUser = 0
    var locationUser = "0"

    companion object {
        fun newInstance() = AddUserFragment()
    }

    private lateinit var viewModel: AddUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val editTextUserName = view?.findViewById<EditText>(R.id.editTextUserName)
        val editTextSurnameUser = view?.findViewById<EditText>(R.id.editTextSurnameUser)
        val editTextEmailUser = view?.findViewById<EditText>(R.id.editTextEmailUser)
        val editTextPassword = view?.findViewById<EditText>(R.id.editTextPassword)
        val editTextPhone = view?.findViewById<EditText>(R.id.editTextPhone)
        val editTextCompanyUser = view?.findViewById<EditText>(R.id.editTextCompanyUser)
        val spinnerUserLocation = view?.findViewById<Spinner>(R.id.spinnerUserLocation)
        val btnAddNewUser = view?.findViewById<Button>(R.id.btnAddNewUser)
        val spinnerUserStatus = view?.findViewById<Spinner>(R.id.spinnerUserStatus)
        //satusID
        val adapterUserStatus = ArrayAdapter.createFromResource(requireContext(), R.array.user_staus, R.layout.spinner_item)
        adapterUserStatus.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerUserStatus?.adapter = adapterUserStatus
        spinnerUserStatus?.onItemSelectedListener = this


        viewModel = ViewModelProvider(this).get(AddUserViewModel::class.java)

            val listLocation = context?.let {
                ArrayAdapter<Any>(it, R.layout.spinner_item)
            }

            viewModel.getListLocation(requireContext())?.observe(viewLifecycleOwner,{ location->
                location?.forEach {
                    listLocation?.add(it.city+ ", " + it.zipCode)
                }
            })
        listLocation?.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerUserLocation?.adapter = listLocation
        spinnerUserLocation?.onItemSelectedListener = this

        btnAddNewUser?.setOnClickListener {

            viewModel.addNewUser(requireContext(),UUID.randomUUID().toString(),editTextUserName?.text.toString(), editTextSurnameUser?.text.toString(),editTextEmailUser?.text.toString(),editTextPassword?.text.toString(),statusUser, locationUser.toString(),editTextPhone?.text.toString(),editTextCompanyUser?.text.toString())
        }
    }


    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        when(parent.id){
            R.id.spinnerUserStatus ->  statusUser = pos
            R.id.spinnerUserLocation -> locationUser = (pos).toString()
        }

    }

    override fun onNothingSelected(parent: AdapterView<*>) {

    }
}



