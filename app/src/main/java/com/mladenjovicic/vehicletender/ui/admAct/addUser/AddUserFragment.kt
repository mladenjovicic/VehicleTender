package com.mladenjovicic.vehicletender.ui.admAct.addUser

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.ViewModelsProviderUtils
import java.util.*

class AddUserFragment : Fragment(), AdapterView.OnItemSelectedListener  {
    var statusUser = 0
    var statusUserServer = ""
    var locationUser = "0"

    companion object {
        fun newInstance() = AddUserFragment()
    }

    private lateinit var viewModel: AddUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_user, container, false)
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

        viewModel = ViewModelsProviderUtils.AddNewUser(this)
            //location
            val listLocation = context?.let {
                ArrayAdapter<Any>(it, R.layout.spinner_item)
            }

            viewModel.getListLocation()?.observe(viewLifecycleOwner,{ location->
                location?.forEach {
                    listLocation?.add(it.city+ ", " + it.zipCOde)
                }
            })
            listLocation?.setDropDownViewResource(R.layout.spinner_dropdown_item)
            spinnerUserLocation?.adapter = listLocation
            spinnerUserLocation?.onItemSelectedListener = this
            //role
            val listRole =context?.let {
                ArrayAdapter<Any>(it, R.layout.spinner_item)
            }
            viewModel.getListUserRole()?.observe(viewLifecycleOwner,{role->
                role?.forEach {
                    listRole?.add(it.RoleId)
                }
            })
            listRole?.setDropDownViewResource(R.layout.spinner_dropdown_item)
            spinnerUserStatus?.adapter = listRole
            spinnerUserStatus?.onItemSelectedListener = this


        val sharedPreferences = requireActivity().getSharedPreferences("UserDate", Context.MODE_PRIVATE)
        var token =sharedPreferences.getString("token", "null")


        btnAddNewUser?.setOnClickListener {
            viewModel.createNewUser(token!!, null, editTextEmailUser?.text.toString(), null, locationUser.toInt(), true, editTextUserName?.text.toString(),
            editTextSurnameUser?.text.toString(), editTextPhone?.text.toString(), "null",statusUserServer, editTextCompanyUser?.text.toString(), editTextPassword?.text.toString())
            //viewModel.addNewUser(UUID.randomUUID().toString(),editTextUserName?.text.toString(), editTextSurnameUser?.text.toString(),editTextEmailUser?.text.toString(),editTextPassword?.text.toString(),statusUser, locationUser.toString(),editTextPhone?.text.toString(),editTextCompanyUser?.text.toString())

            viewModel.requestState.observe(requireActivity()) {
                if(it.pending)
                    Log.e("Loading", "retrofit request is in progress, show loading spinner")
                if(it.successful){
                    viewModel.createNewUserAPI.observe(requireActivity()){
                        var userRoleNo = 1
                        if(it != null){
                            when(it.RoleName){
                                "admin"->userRoleNo = 2
                                "user" ->userRoleNo = 0
                            }
                            viewModel.addNewUser(it.ID!!, it.FirstName!!, it.LastName!!, it.Email!!, "", userRoleNo, it.LocationId.toString(), "it.PhoneNumber!!", it.DealerName!!)
                            editTextCompanyUser?.text?.clear()
                            editTextEmailUser?.text?.clear()
                            editTextPassword?.text?.clear()
                            editTextSurnameUser?.text?.clear()
                            editTextUserName?.text?.clear()
                            Toast.makeText(requireContext(),"user add", Toast.LENGTH_SHORT).show()
                        }
                    }


                    Log.e("Success", "retrofit request is successful")


                }
                else {
                    Log.e("error", "retrofit request is ${it.errorMessage}")
                }
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        when(parent.id){
            R.id.spinnerUserStatus ->  {
                statusUser = pos
                getRoleID(pos)
            }
            R.id.spinnerUserLocation -> {
                getLocationID(pos)
            }
        }
    }

    fun getLocationID(pos: Int){
        viewModel.getListLocation()?.observe(viewLifecycleOwner,{ location->
            location?.forEach {
                locationUser = location[pos].idServer.toString()
            }
        })
    }

    fun getRoleID(pos: Int){
        viewModel.getListUserRole()?.observe(viewLifecycleOwner, {role->
            role?.forEach {
                statusUserServer = role[pos].ServerId

            }

        })
    }

    override fun onNothingSelected(parent: AdapterView<*>) {

    }
}



