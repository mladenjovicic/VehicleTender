package com.mladenjovicic.vehicletender.adapter

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
import com.mladenjovicic.vehicletender.data.model.db.BidModelDB
import com.mladenjovicic.vehicletender.ui.winBid.WinBidViewModel

class BidWinAdapter (val activity: Fragment, val viewModel: WinBidViewModel, lifecycleOwner: LifecycleOwner):RecyclerView.Adapter<BidWinAdapter.MyViewHolder>(){
    var lifecycleOwner = lifecycleOwner
    var token = ""
    var carId = 0
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

        holder.btnAddBid.setOnClickListener {
            viewModel.updateWin(token, bidModelList!![position].serverId,bidModelList!![position].userId.toString().toInt(), bidModelList!![position].stockId, bidModelList!![position].price, true,true)
            viewModel.updateCarStock(token, carId, true)

            println("dev 123" + bidModelList!![position].stockId)

            viewModel.requestState.observe(lifecycleOwner){
                if(it.pending)
                    Log.e("Loading", "retrofit request is in progress, show loading spinner")
                if(it.successful){
                    Log.e("Success", "retrofit request is successful bid win " )
                    viewModel.getUpdateBidObserver().observe(lifecycleOwner, Observer {
                        if(it!=null){
                            Toast.makeText(holder.itemView.context, "", Toast.LENGTH_SHORT).show()
                            viewModel.updateWinDB(it.ID!!, it.TUserId!!, it.TStockId!!,it.Price!!, it.IsWinningPrice!!, it.isActive!!)
                        }
                    })
                    viewModel.requestState.observe(lifecycleOwner){
                        if(it.pending)
                            Log.e("Loading", "retrofit request is in progress, show loading spinner")
                        if(it.successful){
                            Log.e("Success", "retrofit request is successful bid win " )
                            viewModel.getUpdateCarStockObserver().observe(lifecycleOwner, Observer {
                                if(it!=null){
                                    var year = 0
                                    if (it.year == null){
                                        year = 9999
                                    }else year = it.year!!
                                    viewModel.updateCarStockDB(it.id!!, year, it.modelLineId!!,it.mileage!!, it.price!!, it.comments!!, it.locationId!!, it.regNo!!, true)
                                }
                            })
                        }
                    }
                }
            }
        }
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
            textViewUserID.visibility = View.GONE
            editTextPriceBid.visibility = View.GONE
            textViewUserModelName.visibility = View.GONE
            textViewUserModelNo.visibility = View.GONE

            textViewUserCarMileageShow.visibility = View.GONE
            textViewUserCarPriceShow.visibility = View.GONE
            textViewUserCarCityShow.visibility = View.GONE
            textViewUserCarRegShow.visibility = View.GONE
            textViewUserCarComementShow.visibility = View.GONE
            btnAddBid.setText("win")
            textViewUserManufacturerName.text = " Car price: "
            textViewUserCarYearShow.text = ""+date.price + " $"
        }

    }
}
