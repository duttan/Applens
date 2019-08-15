package com.example.applens.createticket

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel;
import com.example.applens.Database.ApplensDatabaseDao
import com.example.applens.Database.Ticket
import kotlinx.coroutines.*

class MainViewModel(val database: ApplensDatabaseDao, application: Application): AndroidViewModel(application)
{
    // TODO: Implement the ViewModel
    private var viewModelJob = Job()

    var alltickets = database.getAllTickets()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    fun retriveTickets(){
        uiScope.launch {
            val tics = getTickets()

        }

    }


    private suspend fun getTickets() {
        withContext(Dispatchers.IO) {



        }

    }
}
