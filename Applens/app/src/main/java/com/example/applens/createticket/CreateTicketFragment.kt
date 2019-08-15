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
     class MyTicket(var id: String = "",
                        var desc: String = "",
                        var status: String = "",
                        var tik_type: String = "",
                        var projectname: String = "",
                        var priority: String = "",
                        var application_name: String = "")


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {



        val binding: AddTicketBinding = DataBindingUtil.inflate(inflater, R.layout.add_ticket, container, false)
        val application = requireNotNull(this.activity).application

        val dataSource = ApplensDatabase.getInstance(application).applensDatabaseDao
        val viewModelFactory = CreateTicketViewModelFactory(dataSource, application)

        val createTicketViewModel = ViewModelProviders.of(this, viewModelFactory).get(CreateTicketViewModel::class.java)

        binding.createTicketViewModel = createTicketViewModel

        binding.createTikButton.setOnClickListener {
            var a:String = binding.ticketId.text.toString()
            var b:String = binding.ticketDesc.text.toString()
            var c:String = binding.spinnerStatus.selectedItem.toString()
            var d:String = binding.spinnerTicketType.selectedItem.toString()
            var e:String = binding.spinnerProjname.selectedItem.toString()
            var f:String = binding.spinnerPriority.selectedItem.toString()
            var g:String = binding.spinnerApplication.selectedItem.toString()

            var myticket = MyTicket(a,b,c,d,e,f,g)

            createTicketViewModel.onCreateclick(myticket)
            activity!!.onBackPressed()
        }

          //  createTicketViewModel.onCreateclick(binding.ticketId.text.toString(),binding.ticketDesc.text.toString()) }


        binding.closeButton.setOnClickListener { activity!!.onBackPressed(); }

        binding.setLifecycleOwner(this)





        return binding.root
    }
}
