package com.example.applens.Database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.applens.createticket.Efforts

class TicketRepository (private val applensDatabaseDao: ApplensDatabaseDao)     {
    val allTickets: LiveData<List<Ticket>> = applensDatabaseDao.getAllTickets()

    @WorkerThread
    suspend fun insert(ticket: Ticket) {
        applensDatabaseDao.insert(ticket)
    }


    fun update(ticket: Ticket) {
        applensDatabaseDao.update(ticket)
    }




}