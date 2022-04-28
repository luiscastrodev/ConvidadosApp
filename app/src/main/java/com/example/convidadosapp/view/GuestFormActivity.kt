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

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var _binding: ActivityGuestFormBinding
    private lateinit var mViewModel : GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
        observe()

        mViewModel.get(1)
    }

    override fun onClick(v: View) {
        val id = v.id

        if (id == R.id.button_save){

            val name = _binding.editName.text.toString()
            val presence = _binding.radioPresence.isChecked

            mViewModel.save(name, presence)
        }
    }

    private fun observe(){
        mViewModel.saveGuest.observe(this, {
            if(it){
                Toast.makeText(applicationContext,"Sucesso",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext,"Erro",Toast.LENGTH_SHORT).show()
            }
        })

        mViewModel.getGuest.observe(this,{
            Toast.makeText(applicationContext,it.name,Toast.LENGTH_SHORT).show()
        })
    }

    private fun setListeners(){
        _binding.buttonSave.setOnClickListener(this)
    }


}