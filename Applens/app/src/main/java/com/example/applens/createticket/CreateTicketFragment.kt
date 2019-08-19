package com.example.applens.createticket

import android.os.Bundle
import android.util.Log
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
import java.text.DateFormat
import java.text.SimpleDateFormat

import java.util.*




class CreateTicketFragment: Fragment()

{
     class MyTicket(var id: String = "",
                        var desc: String = "",
                        var status: String = "",
                        var tik_type: String = "",
                        var projectname: String = "",
                        var priority: String = "",
                        var application_name: String = "",
                        var openDate: String,
                        var closeDate: String)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val datevalue = arguments!!.getString("MyDateKey")

        var formatter = SimpleDateFormat("YYYY-MM-dd")
        val binding: AddTicketBinding = DataBindingUtil.inflate(inflater, R.layout.add_ticket, container, false)
        val application = requireNotNull(this.activity).application

        val dataSource = ApplensDatabase.getInstance(application).applensDatabaseDao
        val viewModelFactory = CreateTicketViewModelFactory(dataSource, application)

        var dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val datt = dateFormat.parse(datevalue)
        val od = Calendar.getInstance()
        od.time = datt

        Log.i("@@",od.time.toString()+ "\n")
        Log.i("@@",datt.toString() + "\n")

        val opendate = formatter.format(od.time)
        od.add(Calendar.DAY_OF_MONTH,15)
        var closedate  = formatter.format(od.time)



        Log.i("@@@",opendate.toString() +" "+closedate.toString()+" "+datevalue)

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



            var myticket = MyTicket(a,b,c,d,e,f,g,opendate,closedate)

            createTicketViewModel.onCreateclick(myticket)
            activity!!.onBackPressed()
        }

          //  createTicketViewModel.onCreateclick(binding.ticketId.text.toString(),binding.ticketDesc.text.toString()) }

        binding.opendate.text = "OpenDate: "+datevalue.toString()
        binding.closeButton.setOnClickListener { activity!!.onBackPressed(); }

        binding.setLifecycleOwner(this)





        return binding.root

    }
}
