package com.example.kotlintask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlintask.DTOs.LocationDTO
import com.example.kotlintask.R

class AdapterHomeAddress(private val newList: ArrayList<LocationDTO>) : RecyclerView.Adapter<AdapterHomeAddress.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.home_fragment_list,
        parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newList[position]
        holder.titleText.text = currentItem.Name
    }

    override fun getItemCount(): Int {

        return newList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val image : ImageView = itemView.findViewById(R.id.image)
        val titleText : TextView = itemView.findViewById(R.id.title_text)

    }
}