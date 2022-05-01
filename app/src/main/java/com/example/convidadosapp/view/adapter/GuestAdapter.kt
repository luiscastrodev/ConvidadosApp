package com.example.convidadosapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.convidadosapp.R
import com.example.convidadosapp.service.model.GuestModel
import com.example.convidadosapp.view.listener.IGuestListener
import com.example.convidadosapp.view.viewholder.GuestViewHolder

class GuestAdapter() : RecyclerView.Adapter<GuestViewHolder>() {

    private var mGuestList: List<GuestModel> = arrayListOf()

    private lateinit var mListener: IGuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_guest, null)
        return GuestViewHolder(item, mListener)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(mGuestList[position])
    }

    override fun getItemCount(): Int {
        return mGuestList.count()
    }

    fun updateGuests(lsit: List<GuestModel>) {
        mGuestList = lsit
        notifyDataSetChanged()
    }

    fun attachListener(listener: IGuestListener) {
        mListener = listener
    }
}