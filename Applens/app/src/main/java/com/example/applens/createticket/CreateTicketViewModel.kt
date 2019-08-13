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






    fun onCreateclick(id: String, desc: String) {
        uiScope.launch {

            var ticket = Ticket(id,desc)
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