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

    private var ticket = MutableLiveData<Ticket?>()

   // private val tickets = database.getAllTickets()

    private val _desc = MutableLiveData<String>()
    val desc: LiveData<String>
        get() = _desc




    fun onCreateclick() {
        uiScope.launch {

        Log.i("@@","H"+ desc.value)


        }
    }



//    private suspend fun insert(ticket: Ticket) {
//        withContext(Dispatchers.IO) {
//            database.insert(ticket)
//        }
//    }



}