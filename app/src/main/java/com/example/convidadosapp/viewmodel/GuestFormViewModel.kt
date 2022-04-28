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
//precisavos estender de AndroidViewModel para usar contexto
class GuestFormViewModel (application: Application): AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val mGuestRespository: GuestRespository = GuestRespository.getInstance(mContext)

    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest

    fun save(name: String, presence: Boolean) {
        mGuestRespository.save(GuestModel(name, presence))
    }
}