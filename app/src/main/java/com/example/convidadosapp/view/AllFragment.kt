package com.example.convidadosapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.convidadosapp.databinding.FragmentAllguestsBinding
import com.example.convidadosapp.view.adapter.GuestAdapter
import com.example.convidadosapp.viewmodel.AllViewModel

class AllFragment : Fragment() {

    private lateinit var allViewModel: AllViewModel
    private var _binding: FragmentAllguestsBinding? = null
    private val mAdapter: GuestAdapter = GuestAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        allViewModel = ViewModelProvider(this)[AllViewModel::class.java]

        _binding = FragmentAllguestsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recycler = binding.recyclerAllGuests

        recycler.layoutManager = LinearLayoutManager(context)

        recycler.adapter = mAdapter

        allViewModel.load()

        observer()

        return root
    }

    private fun observer(){
        allViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}