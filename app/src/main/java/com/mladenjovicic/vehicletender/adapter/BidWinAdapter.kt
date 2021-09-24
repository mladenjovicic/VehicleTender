package com.mladenjovicic.vehicletender.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.data.model.db.BidModelDB
import com.mladenjovicic.vehicletender.ui.userBid.UserBidTenderViewModel
import com.mladenjovicic.vehicletender.ui.winBid.WinBidViewModel

class BidWinAdapter (val activity: Fragment/*, val viewModel: WinBidViewModel*/):RecyclerView.Adapter<BidWinAdapter.MyViewHolder>(){


    private var bidModelList:List<BidModelDB>?=null
    fun setBidActiveList(BidActiveList: List<BidModelDB>){
        this.bidModelList  = BidActiveList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_car_bid, parent, false)
        return BidWinAdapter.MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(bidModelList?.get(position)!!, activity)
    }

    override fun getItemCount(): Int {
        if(bidModelList == null) return 0
        else return bidModelList?.size!!
    }

    class  MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

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

        fun bind(date:BidModelDB, activity: Fragment){
            textViewUserManufacturerName.text = " test  " + date.price
        }

    }
}
