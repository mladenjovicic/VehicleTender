package com.mladenjovicic.vehicletender.adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.data.model.db.TenderFullListID
import com.mladenjovicic.vehicletender.data.model.db.TenderModelDB
import com.mladenjovicic.vehicletender.data.model.db.TenderStockModelDB
import com.mladenjovicic.vehicletender.ui.fragmentuserbidtender.UserBidTenderViewModel

class BidAdapter(val activity: Fragment, userStatus:Int, tenderStatus:Int, val viewModel:UserBidTenderViewModel, uuidUser:String, context: LifecycleOwner): RecyclerView.Adapter<BidAdapter.MyViewHolder>() {
    var userStatus = userStatus
    var tenderStatus = tenderStatus
    var uuidUser = uuidUser
    var context = context

    private var TenderStockList:List<TenderFullListID>?=null

    fun setTenderActivList(TenderActivList: List<TenderFullListID>){
        this.TenderStockList = TenderActivList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BidAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_car_bid, parent, false)
        return BidAdapter.MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: BidAdapter.MyViewHolder, position: Int) {
        holder.bind(TenderStockList?.get(position)!!, activity, userStatus,tenderStatus)
        holder.btnAddBid.setOnClickListener {
            if(holder.editTextPriceBid.text.isNotEmpty()) {
                viewModel.inserBidJSON(
                    99,
                    uuidUser,
                    TenderStockList!![position].stockId,
                    holder.editTextPriceBid.text.toString().toDouble(),
                    false
                )
                viewModel.getNewBidObserver().observe(context, Observer {
                    println("devvv " + it.toString())
                    if(it!=null){
                        viewModel.insertBid(it?.ID!!, it?.TUserId!!, it.TStockId, it.Price, it?.IsWinningPrice!!)
                        Toast.makeText(holder.rowCarBid.context, "Add new bid", Toast.LENGTH_SHORT).show()
                    }

                })

                viewModel.requestState.observe(context) {
                    if(it.pending)
                        Log.e("Loading", "retrofit request is in progress, show loading spinner")

                    if(it.successful)
                        Log.e("Success", "retrofit request is successful")

                }
            }else{
                Toast.makeText(holder.rowCarBid.context, "Sva polja moraju biti popunjena", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        if(TenderStockList == null) return 0
        else return TenderStockList?.size!!
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textViewUserID = itemView.findViewById<TextView>(R.id.textViewUserID)
        val textViewUserManufacturerName = itemView.findViewById<TextView>(R.id.textViewUserManufacturerName)
        val textViewUserModelName = itemView.findViewById<TextView>(R.id.textViewUserModelName)
        val textViewUserModelNo = itemView.findViewById<TextView>(R.id.textViewUserModelNo)
        val textViewUserCarYearShow = itemView.findViewById<TextView>(R.id.textViewUserCarYearShow)
        val textViewUserCarMileageShow = itemView.findViewById<TextView>(R.id.textViewUserCarMileageShow)
        val textViewUserCarPriceShow = itemView.findViewById<TextView>(R.id.textViewUserCarPriceShow)
        val textViewUserCarCityShow = itemView.findViewById<TextView>(R.id.textViewUserCarCityShow)
        val textViewUserCarRegShow = itemView.findViewById<TextView>(R.id.textViewUserCarRegShow)
        val textViewUserCarComementShow = itemView.findViewById<TextView>(R.id.textViewUserCarComementShow)
        val editTextPriceBid = itemView.findViewById<EditText>(R.id.editTextPriceBid)
        val btnAddBid = itemView.findViewById<Button>(R.id.btnAddBid)
        val rowCarBid = itemView.findViewById<LinearLayout>(R.id.rowCarBid)

        fun bind(date:TenderFullListID, activity: Fragment, userStatus: Int, tenderStatus:Int){

            textViewUserManufacturerName.text = "Manufacturer: \n" +  date.manufacturer_name.toString()
            textViewUserModelName.text = "Model name: \n" + date.model_name
            textViewUserModelNo.text = "Model number: \n" + date.model_no
            textViewUserCarYearShow.text = "Car year: \n" + date.year.toString()
            textViewUserCarMileageShow.text = "Car mileage:  \n" + date.mileage.toString()
            textViewUserCarPriceShow.text = "Car price: \n" + date.price.toString() + "$"
            textViewUserCarCityShow.text = "Car location: \n"+date.city
            textViewUserCarRegShow.text = "Car registration: \n" +date.regNo
            textViewUserCarComementShow.text = "Car comment: \n"+ date.comments

            if(userStatus == 0){
                editTextPriceBid.visibility = View.VISIBLE
                btnAddBid.visibility = View.VISIBLE
                textViewUserID.visibility = View.VISIBLE
                if(tenderStatus == 1){
                    editTextPriceBid.visibility = View.GONE
                    btnAddBid.visibility = View.GONE
                    textViewUserID.visibility = View.GONE
                }
            }
            if(userStatus == 1 ){
                editTextPriceBid.visibility = View.GONE
                btnAddBid.visibility = View.GONE
                textViewUserID.visibility = View.GONE
            }
            if(userStatus == 2){
                textViewUserID.visibility = View.GONE
                editTextPriceBid.visibility = View.GONE
            }

        }

    }


}