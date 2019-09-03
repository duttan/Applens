package com.example.applens.Database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.*

@Dao
interface ApplensDatabaseDao {

    @Insert
    fun insert(ticket: Ticket)


    @Query("SELECT * from ticket_table")
    fun getAllTickets(): LiveData<List<Ticket>>


    @Query("SELECT * from ticket_table WHERE strftime('%Y/%m/%d',ticketOpenDate) <= strftime('%Y/%m/%d',:key) and strftime('%Y/%m/%d',ticketCloseDate) >= strftime('%Y/%m/%d',:key) and ticketStatus != 'Closed'")
    fun get(key: String): LiveData<List<Ticket>>

    @Query("SELECT * from ticket_table WHERE application = :key1" )
    fun getAllTickets11(key1: String): LiveData<List<Ticket>>

    @Query("SELECT * from ticket_table WHERE ticketActivity = :key1 ")
    fun getAllTickets12(key1: String): LiveData<List<Ticket>>

    @Query("SELECT * from ticket_table WHERE application = :key1 and ticketActivity = :key2")
    fun getAllTickets2(key1: String, key2: String): LiveData<List<Ticket>>

    @Query("SELECT * from ticket_table WHERE ticket_Id = :key1 or ticketStatus = :key2")
    fun getAllTickets3(key1: String, key2: String): LiveData<List<Ticket>>

    @Update
    fun update(ticket: Ticket)

    @Query("Delete from ticket_table")
    fun deleteAll()



}
