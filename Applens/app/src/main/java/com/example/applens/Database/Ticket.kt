package com.example.applens.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "ticket_table")
data class Ticket(

    @PrimaryKey
    var ticket_Id: String,

    @ColumnInfo(name = "ticketDesc")
    val ticket_Desc: String,

    @ColumnInfo(name = "ticketStatus")
    val ticket_Status: String,

    @ColumnInfo(name = "ticketType")
    val ticket_Type: String,

    @ColumnInfo(name = "projectName")
    val projectName: String,

    @ColumnInfo(name = "ticketPriority")
    val ticket_Priority: String,

    @ColumnInfo(name = "application")
    val application: String,

    @ColumnInfo(name = "ticketOpenDate")
    val ticket_openDate: String,

    @ColumnInfo(name = "ticketCloseDate")
    val ticket_closeDate: String,

    @ColumnInfo(name = "ticketService")
    val ticket_Service: String = "",

    @ColumnInfo(name = "ticketActivity")
    val ticket_Activity: String = "",

    @ColumnInfo(name = "ticketEffort")
    val ticket_Effort: Int = 0)








