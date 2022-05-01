package com.example.convidadosapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidadosapp.service.model.GuestModel
import com.example.convidadosapp.repository.GuestRespository

//Para usar context na aplicacao viewmodel ou passa por construtor ou passa o aplication
//precisavos estender de AndroidViewModel para usar contexto da aplicacao
class GuestFormViewModel (application: Application): AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val mGuestRespository: GuestRespository = GuestRespository.getInstance(mContext)

    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest

    private var mGetGuest = MutableLiveData<GuestModel>()
    val getGuest: LiveData<GuestModel> = mGetGuest

    fun save(name: String, presence: Boolean) {
       val result = mGuestRespository.save(GuestModel(name = name, presence = presence))
        mSaveGuest.value = result
    }

    fun get(id:Int){

        val result = mGuestRespository.get(id)
        mGetGuest.value = result!!
    }
}