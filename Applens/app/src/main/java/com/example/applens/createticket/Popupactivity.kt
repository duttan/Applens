package com.example.applens.createticket

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.applens.Database.ApplensDatabase
import com.example.applens.R
import java.text.SimpleDateFormat
import java.util.*

class Popupactivity:AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_ticket)

        var formatter = SimpleDateFormat("YYYY-MM-dd")

        val today = Calendar.getInstance()
        today.add(Calendar.DAY_OF_MONTH,0)



        val application = requireNotNull(this).application
        val dataSource = ApplensDatabase.getInstance(application).applensDatabaseDao
        val viewModelFactory = MainViewModelFactory(dataSource,application)
        val popViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)




        var datestring:String =  formatter.format(today.time).toString()
        var recyclerView = findViewById<RecyclerView>(R.id.recycler_popup)
        var button = findViewById<Button>(R.id.submit)

        val adapternew = TicketlistAdapter(this,popViewModel)

        adapternew.setEfforts(popViewModel.getTicketsForDate1(datestring))

        adapternew.efforts
        adapternew.getUpdatedEfforts()

        recyclerView.adapter = adapternew


        button.setOnClickListener {

            var totalEffort : Int = 0
            for (item in adapternew.getUpdatedEfforts()){
                totalEffort =  totalEffort+item.Logged_efforts

            }
            if(totalEffort > 8){
                Toast.makeText(this, "Cannot log more than 8 hours for a day.", Toast.LENGTH_LONG).show()
            }else {

                popViewModel.bulkInsertUpdateEfforts(adapternew.getUpdatedEfforts(), datestring)
                Toast.makeText(this, "Successfully Submitted", Toast.LENGTH_LONG).show()
                onBackPressed()
            }
        }



    }


}