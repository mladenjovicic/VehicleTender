package com.mladenjovicic.vehicletender.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.data.model.db.TenderStockModelDB
import com.mladenjovicic.vehicletender.data.model.db.stockCarList
import com.mladenjovicic.vehicletender.data.model.db.stockCarUpdate
import com.mladenjovicic.vehicletender.ui.tender.TenderUseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarsForSellingAdapter(val activity: Activity,  val viewModel: TenderUseViewModel ): RecyclerView.Adapter<CarsForSellingAdapter.MyViewHolder>()  {

    private var carForSellingList:List<stockCarUpdate>?=null
    fun setCarForSellingList(carStock: List<stockCarUpdate>){
        this.carForSellingList = carStock
    }
    var saleDate = ""
    var tenderId = ""
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarsForSellingAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_car_stock_list, parent, false)
        return CarsForSellingAdapter.MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarsForSellingAdapter.MyViewHolder, position: Int) {
        holder.bind(carForSellingList?.get(position)!!, activity)
        var click = 0
        holder.imageButtonAdd.setOnClickListener {

            var tenderStock:TenderStockModelDB?=null
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.insertTenderStock(carForSellingList!![position].serverId!!,carForSellingList!![position].serverId, tenderId,saleDate)
            }
            Toast.makeText(holder.itemView.context,"Add car in Tender stock", Toast.LENGTH_SHORT).show()

        }

        holder.imageButtonRemove.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
            viewModel.deleteTenderStock(carForSellingList!![position].serverId!!,tenderId,saleDate)}
            Toast.makeText(holder.itemView.context,"Remove car in Tender stock", Toast.LENGTH_SHORT).show()
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
        val imageButtonRemove = itemView.findViewById<ImageView>(R.id.imageButtonRemove)


        fun bind(date:stockCarUpdate, activity: Activity){
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
            if(date.tenderId == null){
                imageButtonAdd.visibility = View.VISIBLE
                imageButtonRemove.visibility = View.GONE
            }else{
                imageButtonAdd.visibility = View.GONE
                imageButtonRemove.visibility = View.VISIBLE
            }

        }


    }
}