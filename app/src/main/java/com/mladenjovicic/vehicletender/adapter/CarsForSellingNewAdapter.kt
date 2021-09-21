package com.mladenjovicic.vehicletender.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.data.model.db.stockCarList
import com.mladenjovicic.vehicletender.ui.tender.TenderUseViewModel
var ListItemBookReturnBinding = R.layout.row_car_stock_list
/*
class CarsForSellingNewAdapter(private val viewModel: TenderUseViewModel):RecyclerView.Adapter<CarsForSellingNewAdapter.CarsForSellingNewViewHolder>() {
    private var carForSellingList:List<stockCarList>?=null
    fun setCarForSellingList(carStock: List<stockCarList>){
        this.carForSellingList = carStock
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarsForSellingNewAdapter.CarsForSellingNewViewHolder {
        val inflater  = LayoutInflater.from(parent.context)
        val binding = ListItemBookReturnBinding.inflate(inflater, parent, false)

        return CarsForSellingNewViewHolder(binding, viewModel::setSelected)
    }

    override fun onBindViewHolder(
        holder: CarsForSellingNewAdapter.CarsForSellingNewViewHolder,
        position: Int
    ) {
        holder.bind(carForSellingList?.get(position)!!, position)
    }

    override fun getItemCount() = carForSellingList!!.size

    class CarsForSellingNewViewHolder(
        val binding: ,
        val onBookSelected: KFunction2<stockCarList, Int, Unit>):RecyclerView.ViewHolder(binding.roor), View.OnClickListener{
        var previousPosition = -1
        init {
            binding.setVariable(BR.clickListener, this)
        }
        override fun onClick(v: View?) {
            val book = binding.bookSelected ?: return

            book.isSelected = !book.isSelected

            binding.bookSelected = book
            onBookSelected(book, previousPosition)
        }

        fun bind(data: stockCarList, position: Int) {
            binding.bookSelected = data
            previousPosition = position
        }
    }



}*/
