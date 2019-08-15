package com.example.applens.Database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ApplensDatabaseDao {

    @Insert
    fun insert(ticket: Ticket)


    @Query("SELECT * from ticket_table")
    fun getAllTickets(): LiveData<List<Ticket>>


}
