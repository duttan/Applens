package com.example.applens.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ticket_table")
data class Ticket(
    @PrimaryKey(autoGenerate = true)
    var ticket_Id: Long = 0L,

    @ColumnInfo(name = "ticketDesc")
    val ticket_Desc: String = "lol2"



)
