package com.example.applens.createticket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.applens.Database.ApplensDatabase
import com.example.applens.R
import com.example.applens.databinding.AddTicketBinding
import kotlinx.android.synthetic.main.add_ticket.*

class CreateTicketFragment: Fragment()

{


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: AddTicketBinding = DataBindingUtil.inflate(inflater, R.layout.add_ticket, container, false)
        val application = requireNotNull(this.activity).application

        val dataSource = ApplensDatabase.getInstance(application).applensDatabaseDao
        val viewModelFactory = CreateTicketViewModelFactory(dataSource, application)

        val createTicketViewModel = ViewModelProviders.of(this, viewModelFactory).get(CreateTicketViewModel::class.java)

        binding.createTicketViewModel = createTicketViewModel

        binding.createTikButton.setOnClickListener { createTicketViewModel.onCreateclick(binding.ticketId.text.toString(),binding.ticketDesc.text.toString()) }


        binding.setLifecycleOwner(this)





        return binding.root
    }
}
