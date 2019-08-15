package com.example.applens.createticket

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.applens.Database.ApplensDatabaseDao
import com.example.applens.Database.Ticket
import kotlinx.coroutines.*

class CreateTicketViewModel(val database: ApplensDatabaseDao,application: Application): AndroidViewModel(application)
{

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    //private var ticket = MutableLiveData<Ticket?>()

    //private var alltickets = MutableLiveData<List<Ticket?>>()

   // private val tickets = database.getAllTickets()






    fun onCreateclick(new_ticket:CreateTicketFragment.MyTicket) {
        uiScope.launch {

            var ticket = Ticket(new_ticket.id,new_ticket.desc,new_ticket.status,new_ticket.tik_type,new_ticket.projectname,new_ticket.priority,new_ticket.application_name)
            insert(ticket)



        }
    }



    private suspend fun insert(ticket: Ticket) {
        withContext(Dispatchers.IO) {
            database.insert(ticket)

            var alltickets = database.getAllTickets()


        }
    }

    private fun printo(tiks: List<Ticket>)
    {
        tiks.forEach { Log.i("@@",it.ticket_Id + it.ticket_Desc) }
    }



}