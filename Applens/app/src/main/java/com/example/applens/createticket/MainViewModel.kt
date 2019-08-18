package com.example.applens.createticket

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.example.applens.Database.ApplensDatabaseDao
import com.example.applens.Database.Ticket
import kotlinx.coroutines.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(val database: ApplensDatabaseDao, application: Application): AndroidViewModel(application)
{
    // TODO: Implement the ViewModel
    private var viewModelJob = Job()

    // var alltickets = database.getAllTickets()

    var dateFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)

    lateinit var alltickets: LiveData<List<Ticket>>

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun retriveTickets(date_str: String): LiveData<List<Ticket>>{

       // var date = dateFormat.parse(date_str)

        return database.get(date_str)
    }



    private suspend fun getTickets() {
        withContext(Dispatchers.IO) {



        }

    }
}
