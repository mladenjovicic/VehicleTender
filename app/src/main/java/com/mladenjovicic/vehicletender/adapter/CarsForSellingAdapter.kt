package com.mladenjovicic.vehicletender.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.model.db.stockCarList

class CarsForSellingAdapter(val activity: Activity): RecyclerView.Adapter<CarsForSellingAdapter.MyViewHolder>()  {

    private var carForSellingList:List<stockCarList>?=null
    fun setCarForSellingList(carStock: List<stockCarList>){
        this.carForSellingList = carStock
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarsForSellingAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_stock_list_row, parent, false)
        return CarsForSellingAdapter.MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarsForSellingAdapter.MyViewHolder, position: Int) {
        holder.bind(carForSellingList?.get(position)!!, activity)
        holder.itemView.setOnClickListener {

        }

    }

    override fun getItemCount(): Int {
        if(carForSellingList == null)return 0
        else return carForSellingList?.size!!
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

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


        fun bind(date:stockCarList, activity: Activity){
            textViewCarRegShow.text = "" + date. regNo
        }


    }
}