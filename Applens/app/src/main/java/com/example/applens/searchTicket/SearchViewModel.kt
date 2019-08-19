package com.example.applens.searchTicket

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.example.applens.Database.ApplensDatabaseDao
import com.example.applens.Database.Ticket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class SearchViewModel(val database: ApplensDatabaseDao, application: Application): AndroidViewModel(application)
{

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var alltickets = database.getAllTickets()


    fun filterTickets11(filter_str1: String): LiveData<List<Ticket>> {
        return database.getAllTickets11(filter_str1)
    }

    fun filterTickets12(filter_str1: String): LiveData<List<Ticket>> {
        return database.getAllTickets12(filter_str1)
    }


    fun filterTickets2(filter_str1: String,filter_str2: String): LiveData<List<Ticket>> {
        return database.getAllTickets2(filter_str1,filter_str2)
    }

    fun filterTickets3(filter_str1: String,filter_str2: String): LiveData<List<Ticket>> {
        return database.getAllTickets3(filter_str1,filter_str2)
    }


}

