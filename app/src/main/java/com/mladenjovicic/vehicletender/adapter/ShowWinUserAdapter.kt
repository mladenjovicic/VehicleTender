package com.mladenjovicic.vehicletender.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.data.model.db.tenderWiningList

class ShowWinUserAdapter(val activity: Fragment):RecyclerView.Adapter<ShowWinUserAdapter.MyViewHolder>() {

    private var tenderWiningList:List<tenderWiningList>?=null
    fun setTenderWiningList(tenderWiningList:List<tenderWiningList>){
        this.tenderWiningList = tenderWiningList
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShowWinUserAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_car_stock_list, parent, false)
        return ShowWinUserAdapter.MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowWinUserAdapter.MyViewHolder, position: Int) {
        holder.bind(tenderWiningList?.get(position)!!, activity)
    }

    override fun getItemCount(): Int {
        if(tenderWiningList == null) return 0
        else return tenderWiningList?.size!!
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

        fun bind(date: tenderWiningList, activity: Fragment){
            textViewManufacturerName.text = "Company: " + date.company_name
            textViewCarYearShow.text = "Name: " + date.contact_name
            textViewCarMileageShow.text = "Surname: " + date.contact_surname
            textViewCarCityShow.text = "Wining price:"
            textViewCarRegShow.text = " " +date.price + " $"
        }
    }
}

