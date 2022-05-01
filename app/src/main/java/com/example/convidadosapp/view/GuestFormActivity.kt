package com.example.convidadosapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidadosapp.viewmodel.GuestFormViewModel
import com.example.convidadosapp.R
import com.example.convidadosapp.databinding.ActivityGuestFormBinding
import com.example.convidadosapp.repository.constants.GuestConstants

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var _binding: ActivityGuestFormBinding
    private lateinit var mViewModel : GuestFormViewModel
    private var mGuestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
        observe()
        loadData()
    }

    private fun loadData(){
        val bundle = intent.extras
        bundle?.let {
            mGuestId = bundle.getInt(GuestConstants.GUESTID)
            mViewModel.get(mGuestId)
        }
    }

    override fun onClick(v: View) {
        val id = v.id

        if (id == R.id.button_save){

            val name = _binding.editName.text.toString()
            val presence = _binding.radioPresence.isChecked

            mViewModel.save(mGuestId,name, presence)

        }
    }

    private fun observe(){
        mViewModel.saveGuest.observe(this, {
            if(it){
                Toast.makeText(applicationContext,"Registro Inserido com Sucesso",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext,"Erro ao Inserir",Toast.LENGTH_SHORT).show()
            }
            this.finish()
        })

        mViewModel.updateGuest.observe(this, {
            if(it){
                Toast.makeText(applicationContext,"Registro Atualizado com Sucesso",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext,"Erro ao Atualizar",Toast.LENGTH_SHORT).show()
            }
            this.finish()
        })

        mViewModel.getGuest.observe(this,{
            _binding.editName.setText(it.name)
            if(it.presence)
            {
                _binding.radioPresence.isChecked = true
            }else{
                _binding.radioAbsent.isChecked = true
            }
        })
    }

    private fun setListeners(){
        _binding.buttonSave.setOnClickListener(this)
    }
}