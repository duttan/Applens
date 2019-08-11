package com.example.applens.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ApplensDatabaseDao {

    @Insert
    fun insert(ticket: Ticket)


    @Query("SELECT * from ticket_table")
    fun getAllTickets(): Ticket?


    }
