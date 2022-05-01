package com.example.convidadosapp.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidadosapp.databinding.FragmentAllguestsBinding
import com.example.convidadosapp.repository.constants.GuestConstants
import com.example.convidadosapp.view.adapter.GuestAdapter
import com.example.convidadosapp.view.listener.IGuestListener
import com.example.convidadosapp.viewmodel.GuestsViewModel

class AllFragment : Fragment(), IGuestListener {

    private lateinit var guestsViewModel: GuestsViewModel
    private var _binding: FragmentAllguestsBinding? = null
    private val mAdapter: GuestAdapter = GuestAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        guestsViewModel = ViewModelProvider(this)[GuestsViewModel::class.java]

        _binding = FragmentAllguestsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recycler = binding.recyclerAllGuests

        recycler.layoutManager = LinearLayoutManager(context)

        recycler.adapter = mAdapter

        observer()

        listener()

        return root
    }

    override fun onResume() {
        super.onResume()
        guestsViewModel.load()
    }

    private fun listener() {
        mAdapter.attachListener(this)
    }

    private fun observer() {
        guestsViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
        })

        guestsViewModel.guestRemove.observe(viewLifecycleOwner,{
            if(it){
                Toast.makeText(context,"Registro Deletado",Toast.LENGTH_SHORT).show()
                mAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(context,"Erro ao Deletar Registro",Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onclick(id: Int) {
        val intent = Intent(context, GuestFormActivity::class.java)
        intent.putExtra(GuestConstants.GUESTID, id)
        startActivity(intent)
    }

    override fun onDelete(id: Int) {
        guestsViewModel.deleteGuest(id)
        guestsViewModel.load()
    }
}