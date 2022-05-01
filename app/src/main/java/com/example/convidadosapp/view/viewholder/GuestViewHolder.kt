package com.example.convidadosapp.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.convidadosapp.R
import com.example.convidadosapp.service.model.GuestModel

class GuestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(guest: GuestModel) {
        val txtName = itemView.findViewById<TextView>(R.id.txt_name)
        txtName.text = guest.name
    }
}