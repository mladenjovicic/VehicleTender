package com.mladenjovicic.vehicletender.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.data.model.db.TenderStockModelDB
import com.mladenjovicic.vehicletender.data.model.db.stockCarUpdate
import com.mladenjovicic.vehicletender.ui.tender.addTenderStock.AddTenderStockViewModel
import com.mladenjovicic.vehicletender.ui.tender.addTenderStock.TenderUseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarsForSellingAdapter(val activity: Activity, val viewModel: AddTenderStockViewModel, token:String, lifecycleOwner: LifecycleOwner ): RecyclerView.Adapter<CarsForSellingAdapter.MyViewHolder>()  {

    private var carForSellingList:List<stockCarUpdate>?=null
    fun setCarForSellingList(carStock: List<stockCarUpdate>){
        this.carForSellingList = carStock
    }
    var saleDate = ""
    var tenderId = ""
    var token = token
    var tenderIdServer = 0
    var lifecycleOwner = lifecycleOwner
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarsForSellingAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_car_stock_list, parent, false)
        return CarsForSellingAdapter.MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarsForSellingAdapter.MyViewHolder, position: Int) {
        holder.bind(carForSellingList?.get(position)!!, activity)

        println("dev3" + carForSellingList!![position].tenderStockServerId+ " " + carForSellingList!![position].tenderStockServerId)

        holder.imageButtonAdd.setOnClickListener {
            println("dev4" + carForSellingList!![position].tenderStockServerId+ " " + carForSellingList!![position].tenderStockServerId)
            if(carForSellingList!![position].tenderId ==null){
            viewModel.addTenderStock(token, null, carForSellingList!![position].serverId, tenderIdServer, saleDate, false)
            var tenderStock:TenderStockModelDB?=null
            var lifecycleOwner = holder.itemView.context as LifecycleOwner
            viewModel.requestState.observe(lifecycleOwner) {
                if(it.pending)
                    Log.e("Loading", "retrofit request is in progress, show loading spinner")
                if(it.successful){
                    Log.e("Success", "retrofit request is successful tender " )
                    viewModel.getNewLocationObserver().observe(lifecycleOwner, Observer{
                        if(it!=null){
                            Toast.makeText(holder.itemView.context, "Request is successful", Toast.LENGTH_SHORT).show()
                            viewModel.insertTenderStock(it.id!!, it.stockId!!, it.tenderId.toString(), it.saleDate!!, it.isDeleted!!)
                            viewModel.createNewTenderStock.postValue(null)
                        }
                    })
                }
            }
            Toast.makeText(holder.itemView.context,"Add car in Tender stock", Toast.LENGTH_SHORT).show()}
           /*if(carForSellingList!![position].isDeleted == true){
                viewModel.addTenderStock(token, carForSellingList!![position].tenderStockServerId, carForSellingList!![position].serverId, tenderIdServer, saleDate, false)
                var tenderStock:TenderStockModelDB?=null
                var lifecycleOwner = holder.itemView.context as LifecycleOwner
                viewModel.requestState.observe(lifecycleOwner) {
                    if(it.pending)
                        Log.e("Loading", "retrofit request is in progress, show loading spinner")
                    if(it.successful){
                        Log.e("Success", "retrofit request is successful tender " )
                        viewModel.getNewLocationObserver().observe(lifecycleOwner, Observer{

                            if(it!=null){
                                Toast.makeText(holder.itemView.context, "Request is successful", Toast.LENGTH_SHORT).show()
                                viewModel.insertTenderStock(it.id!!, it.stockId!!, it.tenderId.toString(), it.saleDate!!, it.isDeleted!!)

                                viewModel.createNewTenderStock.postValue(null)
                            }
                        })
                    }
                }
                Toast.makeText(holder.itemView.context,"Add car in Tender stock", Toast.LENGTH_SHORT).show()}*/

        }

        holder.imageButtonRemove.setOnClickListener {
            println("dev1 " + carForSellingList!![position].isDeleted + " " + carForSellingList!![position].tenderStockServerId)
            if(carForSellingList!![position].tenderId !=null){
                println("dev2" + carForSellingList!![position].tenderStockServerId+ " " + carForSellingList!![position].tenderStockServerId)
                viewModel.updateTenderStockJSON(token, carForSellingList!![position].tenderStockServerId, carForSellingList!![position].serverId, tenderIdServer, saleDate, true)
                var tenderStock:TenderStockModelDB?=null
                var lifecycleOwner = holder.itemView.context as LifecycleOwner
                viewModel.requestState.observe(lifecycleOwner) {
                    if(it.pending)
                        Log.e("Loading", "retrofit request is in progress, show loading spinner")
                    if(it.successful){
                        Log.e("Success", "retrofit request is successful tender " )
                        viewModel.getNewLocationObserver().observe(lifecycleOwner, Observer{

                            if(it!=null){
                                Toast.makeText(holder.itemView.context, "Request is successful", Toast.LENGTH_SHORT).show()
                                viewModel.insertTenderStock(it.id!!, it.stockId!!, null, it.saleDate!!, it.isDeleted!!)
                                //viewModel.deleteTenderStock(it.id!!, it.tenderId!!.toString(), it.saleDate!!)

                                viewModel.createNewTenderStock.postValue(null)
                            }
                        })
                    }
                }
                Toast.makeText(holder.itemView.context,"Remove car in Tender stock", Toast.LENGTH_SHORT).show()}
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

            println("dev789 ${date.isDeleted}, ${date.tenderId}, ${date.serverId}")

            if(date.tenderId ==null ){
                imageButtonAdd.visibility = View.VISIBLE
                imageButtonRemove.visibility = View.GONE
            }
            if(date.tenderId !=null){
                imageButtonAdd.visibility = View.GONE
                imageButtonRemove.visibility = View.VISIBLE
            }

        }


    }
}