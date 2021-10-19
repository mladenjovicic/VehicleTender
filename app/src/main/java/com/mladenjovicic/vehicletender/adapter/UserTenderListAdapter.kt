package com.mladenjovicic.vehicletender.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.data.model.db.UserListStatusId
import com.mladenjovicic.vehicletender.data.model.db.UserModelDB
import com.mladenjovicic.vehicletender.data.model.db.stockCarUpdate
import com.mladenjovicic.vehicletender.ui.tender.AddTenderUser.AddTenderUserViewModel
import com.mladenjovicic.vehicletender.ui.tender.addTenderStock.AddTenderStockViewModel

class UserTenderListAdapter(val activity: Activity, val viewModel: AddTenderUserViewModel, token:String, lifecycleOwner: LifecycleOwner):RecyclerView.Adapter<UserTenderListAdapter.MyViewHolder>() {

    private var userTender:List<UserListStatusId>?=null
    var tenderId = 0
    var userId = ""
    var token = ""
    var lifecycleOwner = lifecycleOwner
    fun setUserTenderList(userTender: List<UserListStatusId>){
        this.userTender = userTender
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserTenderListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_car_stock_list, parent, false)
        return UserTenderListAdapter.MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserTenderListAdapter.MyViewHolder, position: Int) {
        holder.bind(userTender?.get(position)!!, activity)

        holder.imageButtonAdd.setOnClickListener {
            viewModel.insertUserTender(token , null, tenderId, userTender!![position].uuId )
            viewModel.requestState.observe(lifecycleOwner){
                if(it.pending)
                    Log.e("Loading", "retrofit request is in progress, show loading spinner")
                if(it.successful){
                    Log.e("Success", "retrofit request is successful tender " )
                    viewModel.getNetUserTenderObserver().observe(lifecycleOwner, Observer {
                        if(it!== null){
                            Toast.makeText(holder.itemView.context, "Request is successful", Toast.LENGTH_SHORT).show()
                            viewModel.addTenderUser(it.id!!, it.tenderId!!, it.userId!!)
                        }
                    })

                }
            }
        }
    }

    override fun getItemCount(): Int {
        if(userTender == null)return 0
        else return userTender?.size!!
    }

    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val carStockRow = itemView.findViewById<LinearLayout>(R.id.carRow)
        val LinearLayoutCar = itemView.findViewById<LinearLayout>(R.id.LinearLayoutCar)
        val textViewManufacturerName = itemView.findViewById<TextView>(R.id.textViewManufacturerName)
        val textViewModelName = itemView.findViewById<TextView>(R.id.textViewModelName)
        val textViewModelNo = itemView.findViewById<TextView>(R.id.textViewModelNo)
        val textViewCarYearShow = itemView.findViewById<TextView>(R.id.textViewCarYearShow)
        val textViewCarMileageShow = itemView.findViewById<TextView>(R.id.textViewCarMileageShow)
        val textViewCarPriceShow = itemView.findViewById<TextView>(R.id.textViewCarPriceShow)
        val textViewCarCityShow = itemView.findViewById<TextView>(R.id.textViewCarCityShow)
        val textViewCarRegShow = itemView.findViewById<TextView>(R.id.textViewCarRegShow)
        val textViewCarComementShow = itemView.findViewById<TextView>(R.id.textViewCarComementShow)
        val imageButtonAdd = itemView.findViewById<ImageView>(R.id.imageButtonAdd)
        val imageButtonRemove = itemView.findViewById<ImageView>(R.id.imageButtonRemove)

        fun bind(data:UserListStatusId, activity: Activity){
            textViewManufacturerName.text = "Name: " + data.contact_name
            textViewModelName.text = "Surname: " + data.contact_surname
            textViewModelNo.text = "Copany name: " + data.company_name
            textViewCarYearShow.text = "Email" + data.email



            if(data.tenderId== null){
                imageButtonAdd.visibility = View.VISIBLE
                imageButtonRemove.visibility = View.GONE
            }
            if(data.tenderId!= null){
                imageButtonRemove.visibility = View.VISIBLE
                imageButtonAdd.visibility = View.GONE
            }
        }
    }
}

