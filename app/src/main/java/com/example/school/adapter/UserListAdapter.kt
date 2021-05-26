package com.example.school.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.school.R
import com.example.school.adapter.UserListAdapter.ViewHolder
import com.example.school.entity.User

class UserListAdapter : RecyclerView.Adapter<ViewHolder>() {

    private lateinit var listUser: List<User>
    private lateinit var itemClickListener: OnItemClickListener

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userName.text = listUser[position].userName
        holder.itemView.setOnLongClickListener {
            itemClickListener.onItemClick(position)
            true
        }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setData(listUser: List<User>) {
        this.listUser = listUser
        notifyDataSetChanged()
    }

}