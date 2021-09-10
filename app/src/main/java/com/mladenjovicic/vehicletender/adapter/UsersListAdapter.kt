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
import com.mladenjovicic.vehicletender.UpdateUserActivity
import com.mladenjovicic.vehicletender.model.db.UserModelDB

class UsersListAdapter(val activity: Fragment):RecyclerView.Adapter<UsersListAdapter.MyViewHolder>() {

    private var usersList:List<UserModelDB>?=null
    fun setUsersList(usersList: List<UserModelDB>){
        this.usersList = usersList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.users_list_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersListAdapter.MyViewHolder, position: Int) {
        holder.bind(usersList?.get(position)!!, activity)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.userRow.context, UpdateUserActivity::class.java)
            intent.putExtra("userId", usersList!![position].Id)
            holder.userRow.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        if(usersList == null)return 0
        else return usersList?.size!!
    }


    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val userRow = itemView.findViewById<LinearLayout>(R.id.carRow)
        val textViewUserNameSurname = itemView.findViewById<TextView>(R.id.textViewUserNameSurname)
        val textViewUserEmailAndPhone =itemView.findViewById<TextView>(R.id.textViewUserEmailAndPhone)
        val textViewUserStatus = itemView.findViewById<TextView>(R.id.textViewUserStatus)
        fun bind(data:UserModelDB, activity: Fragment){
            var userStatus= ""

            println("test22" + data.email)

            textViewUserNameSurname.text = "Name: " + data.contact_name + " " +data.contact_surname
            textViewUserEmailAndPhone.text = "Email: " + data.email + ", Phone:  " + data.phone
            when(data.status_user){
                0->userStatus = "Approved"
                1->userStatus = "Delisted"
                2->userStatus = "Admin"
            }
            textViewUserStatus.text ="Status: " + userStatus
        }
    }
}