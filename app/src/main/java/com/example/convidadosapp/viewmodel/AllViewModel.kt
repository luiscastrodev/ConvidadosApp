package com.example.convidadosapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidadosapp.repository.GuestRespository
import com.example.convidadosapp.service.model.GuestModel

class AllViewModel(application: Application) : AndroidViewModel(application) {

    private val mGuestRespository: GuestRespository = GuestRespository.getInstance(application)
    private val mGuestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = mGuestList

    fun load(){
       mGuestList.value = mGuestRespository.getAll()
    }
}