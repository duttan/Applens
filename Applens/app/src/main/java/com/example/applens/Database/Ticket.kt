package com.example.applens.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ticket_table")
data class Ticket(

    @PrimaryKey
    var ticket_Id: String ,

    @ColumnInfo(name = "ticketDesc")
    val ticket_Desc: String

)
