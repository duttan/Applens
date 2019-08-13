package com.example.applens.createticket

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.applens.Database.ApplensDatabase

import com.example.applens.databinding.FirstPageBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.applens.R



class MainFragment : Fragment() {


    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FirstPageBinding = DataBindingUtil.inflate(inflater, R.layout.first_page, container, false)
        val application = requireNotNull(this.activity).application

        val dataSource = ApplensDatabase.getInstance(application).applensDatabaseDao
        val viewModelFactory = MainViewModelFactory(dataSource,application)
        val mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        binding.mainViewModel = mainViewModel



        binding.fab.setOnClickListener {

            val createTicketFragment = CreateTicketFragment()
            val transaction = fragmentManager!!.beginTransaction()
            transaction.add(R.id.root_layout,createTicketFragment).addToBackStack(null).commit()

        }

        //val adapter = TicketlistAdapter()
       // binding.recyclerViewTicketlist.adapter = adapter

        //mainViewModel.alltickets.observe(viewLifecycleOwner, Observer { it?.let { adapter.data = it } })


        binding.setLifecycleOwner(this)

       return binding.root
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
