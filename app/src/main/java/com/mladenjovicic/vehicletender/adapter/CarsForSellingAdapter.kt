package com.mladenjovicic.vehicletender.adapter

import android.app.Activity
import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.model.db.TenderStockModelDB
import com.mladenjovicic.vehicletender.model.db.stockCarList
import com.mladenjovicic.vehicletender.repository.db.dbRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarsForSellingAdapter(val activity: Activity): RecyclerView.Adapter<CarsForSellingAdapter.MyViewHolder>()  {

    private var carForSellingList:List<stockCarList>?=null
    fun setCarForSellingList(carStock: List<stockCarList>){
        this.carForSellingList = carStock
    }
    var saleDate = ""
    var tenderId = ""
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarsForSellingAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_stock_list_row, parent, false)
        return CarsForSellingAdapter.MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarsForSellingAdapter.MyViewHolder, position: Int) {
        holder.bind(carForSellingList?.get(position)!!, activity)
        var click = 1
        holder.imageButtonAdd.setOnClickListener {

            var tenderStock:TenderStockModelDB?=null


            tenderStock = TenderStockModelDB(carForSellingList!![position].Id!!, tenderId, saleDate)

            if(click == 1){
                CoroutineScope(Dispatchers.IO).launch {
                    dbRepository.roomDB?.tenderStockDAO()?.deleteTenderStock(carForSellingList!![position].Id!!,tenderId)}
            CoroutineScope(Dispatchers.IO).launch {
            dbRepository.roomDB?.tenderStockDAO()?.InsertTenderStock(tenderStock)
                click = 0

            }
                Toast.makeText(holder.itemView.context,"Add car in Tender stock", Toast.LENGTH_SHORT).show()
            }else{
                click = 1
                CoroutineScope(Dispatchers.IO).launch {
                dbRepository.roomDB?.tenderStockDAO()?.deleteTenderStock(carForSellingList!![position].Id!!,tenderId)}
                Toast.makeText(holder.itemView.context,"Remove car in Tender stock", Toast.LENGTH_SHORT).show()
            }
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
        val imageButtonAdd = itemView.findViewById<ImageView>(R.id.imageButtonAdd)
        //val imageButtonRemove = itemView.findViewById<ImageView>(R.id.imageButtonRemove)


        fun bind(date:stockCarList, activity: Activity){
            imageButtonAdd.visibility = View.VISIBLE
            textViewManufacturerName.text ="Manufacturer: \n" +  date.manufacturer_name.toString()
            textViewModelName.text = "Model name: \n" + date.model_name
            textViewModelNo.text = "Model number: \n" + date.model_no
            textViewCarYearShow.text ="Car year: \n" + date.year.toString()
            textViewCarMileageShow.text = "Car mileage:  \n" + date.mileage.toString()
            textViewCarPriceShow.text = "Car price: \n" + date.price.toString() + "$"
            textViewCarCityShow.text = "Car location: \n"+date.city
            textViewCarRegShow.text = "Car registration: \n" +date.regNo
            textViewCarComementShow.text = "Car comment: \n"+ date.comments

        }


    }
}