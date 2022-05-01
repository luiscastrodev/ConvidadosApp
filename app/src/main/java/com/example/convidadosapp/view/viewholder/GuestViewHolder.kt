package com.example.convidadosapp.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.convidadosapp.R
import com.example.convidadosapp.service.model.GuestModel
import com.example.convidadosapp.view.listener.IGuestListener

class GuestViewHolder(itemView: View,private val listener: IGuestListener) : RecyclerView.ViewHolder(itemView) {

    fun bind(guest: GuestModel) {
        val txtName = itemView.findViewById<TextView>(R.id.txt_name)
        txtName.text = guest.name

        itemView.setOnClickListener {
            listener.onclick(guest.id)
        }

        txtName.setOnLongClickListener {

            AlertDialog.Builder(itemView.context)
                .setTitle("Atencao")
                .setMessage("Deseja remover o registro")
                .setPositiveButton("Confirmar") { dialog, wich ->
                    listener.onDelete(guest.id)
                }
                .setNegativeButton("Cancelar"){
                    dialog,wich->
                    dialog.dismiss()
                }
                .show()
            true
        }
    }
}