package com.mladenjovicic.vehicletender.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.data.model.db.stockCarList

class CarsStockAdapter(val activity: Fragment): RecyclerView.Adapter<CarsStockAdapter.MyViewHolder>() {

    private var carStockList:List<stockCarList>?=null
    fun setCarCtockList(carStock: List<stockCarList>){
        this.carStockList = carStock
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarsStockAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_stock_list_row, parent, false)
        return CarsStockAdapter.MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarsStockAdapter.MyViewHolder, position: Int) {
        holder.bind(carStockList?.get(position)!!, activity)
        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        if(carStockList == null)return 0
        else return carStockList?.size!!
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



        fun bind(date: stockCarList, activity: Fragment){
            textViewManufacturerName.text ="Manufacturer: \n" +  date.manufacturer_name.toString()
            textViewModelName.text = "Model name: \n" + date.model_name
            textViewModelNo.text = "Model number: \n" + date.model_no
            textViewCarYearShow.text ="Car year: \n" + date.year.toString()
            textViewCarMileageShow.text = "Car mileage:  \n" + date.mileage.toString()
            textViewCarPriceShow.text = "Car price: \n" + date.price.toString() + "$"
            textViewCarCityShow.text = "Car location: \n"+date.city
            textViewCarRegShow.text = "Car registration: \n" +date.regNo
            textViewCarComementShow.text = "Car comment: \n"+ date.comments

            if(date.isSold ==false){
                LinearLayoutCar.setBackgroundColor(Color.parseColor("#65b473"))
            }else{
                LinearLayoutCar.setBackgroundColor(Color.parseColor("#e51b23"))
            }
        }
    }
}