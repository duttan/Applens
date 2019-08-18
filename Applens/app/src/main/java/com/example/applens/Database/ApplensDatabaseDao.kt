package com.example.applens.Database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.*

@Dao
interface ApplensDatabaseDao {

    @Insert
    fun insert(ticket: Ticket)


    @Query("SELECT * from ticket_table")
    fun getAllTickets(): LiveData<List<Ticket>>


    @Query("SELECT * from ticket_table WHERE strftime('%Y/%m/%d',ticketOpenDate) <= strftime('%Y/%m/%d',:key) and strftime('%Y/%m/%d',ticketCloseDate) >= strftime('%Y/%m/%d',:key)")
    fun get(key: String): LiveData<List<Ticket>>


    // SELECT * from ticket_table WHERE strftime('%Y/%m/%d',ticketOpenDate) <= strftime('%Y/%m/%d','2019-08-20') and strftime('%Y/%m/%d',ticketCloseDate) >= strftime('%Y/%m/%d','2019-08-20')


}
