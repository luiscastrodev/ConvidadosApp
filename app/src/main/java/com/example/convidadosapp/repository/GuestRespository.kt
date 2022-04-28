package com.example.convidadosapp.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidadosapp.repository.constants.DataBaseConstants
import com.example.convidadosapp.service.model.GuestModel
import java.lang.Exception

class GuestRespository private constructor(context: Context) {

    //singleton
    private var mGuestDataBaseHelper: GuestDataBaseHelper = GuestDataBaseHelper(context = context)

    companion object {
        private lateinit var repository: GuestRespository
        fun getInstance(context: Context): GuestRespository {

            if (!::repository.isInitialized) {//verifica se ja foi instanciado
                repository = GuestRespository(context)
            }
            return repository
        }
    }

    fun save(guest: GuestModel): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase
            val value = ContentValues()
            value.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            value.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)
            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, value)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAll(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getPresent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getAbsent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun update(guest: GuestModel) {

    }

    fun remove(guest: GuestModel) {

    }
}