package com.example.convidadosapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidadosapp.repository.GuestRespository
import com.example.convidadosapp.repository.constants.GuestConstants
import com.example.convidadosapp.service.model.GuestModel
import java.util.logging.Filter

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val mGuestRespository: GuestRespository = GuestRespository(application)
    private val mGuestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = mGuestList

    private val mGuestRemove = MutableLiveData<Boolean>()
    var guestRemove: MutableLiveData<Boolean> = mGuestRemove

    fun load(filter: Int = 0) {

        if(filter == GuestConstants.FILTER.EMPTY){
            mGuestList.value = mGuestRespository.getAll()
        }

        if(filter == GuestConstants.FILTER.PRESENTE){
            mGuestList.value = mGuestRespository.getPresent()
        }

        if(filter == GuestConstants.FILTER.ABSENT){
            mGuestList.value = mGuestRespository.getAbsent()
        }

    }

    fun deleteGuest(id: Int) {
        val guest  = mGuestRespository.get(id)
        guestRemove.value =  true//mGuestRespository.remove(guest)
    }
}