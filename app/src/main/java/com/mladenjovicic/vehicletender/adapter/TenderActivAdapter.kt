package com.mladenjovicic.vehicletender.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mladenjovicic.vehicletender.R
import com.mladenjovicic.vehicletender.TenderUseActivity2
import com.mladenjovicic.vehicletender.data.model.db.TenderModelDB

class TenderActivAdapter(val activity: Fragment): RecyclerView.Adapter<TenderActivAdapter.MyViewHolder>()  {

    private var TenderActivList:List<TenderModelDB>?=null

    fun setTenderActivList(TenderActivList: List<TenderModelDB>){
        this.TenderActivList = TenderActivList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TenderActivAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tender_list_row, parent, false)
        return TenderActivAdapter.MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: TenderActivAdapter.MyViewHolder, position: Int) {
        holder.bind(TenderActivList?.get(position)!!, activity)
        holder.itemView.setOnClickListener {

            if(holder.rowTenders != null){
                val intent = Intent(holder.rowTenders.context, TenderUseActivity2::class.java)
                intent.putExtra("tenderNo", TenderActivList!![position].tenderNo)
                intent.putExtra("statusId", TenderActivList!![position].statusId)
                holder.rowTenders.context.startActivity(intent)
            }else{
                val intent = Intent(holder.textViewTenderId?.context, TenderUseActivity2::class.java)
                intent.putExtra("tenderNo", TenderActivList!![position].tenderNo)
                holder.textViewTenderId?.context!!.startActivity(intent)
            }

        }
    }

    override fun getItemCount(): Int {
        if(TenderActivList == null) return 0
        else return TenderActivList?.size!!
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textViewTenderId = itemView.findViewById<TextView>(R.id.textViewTenderId)
        val textViewOpenDate = itemView.findViewById<TextView>(R.id.textViewOpenDate)
        val textViewCloseDate = itemView.findViewById<TextView>(R.id.textViewCloseDate)
        val rowTenders = itemView.findViewById<LinearLayout>(R.id.rowTenders)

        fun bind(date: TenderModelDB, activity: Fragment){
            textViewTenderId.text = "Tender number: " + date.tenderNo
            textViewOpenDate.text = "Open date tender: " + date.openDate
            textViewCloseDate.text ="Close date tender: " + date.closeDate
        }
    }
}