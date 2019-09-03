package com.example.applens.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timesheet_table")
data class TimeSheet (


    @PrimaryKey @ColumnInfo(name = "TimeSheetID") val ID: String,

    @ColumnInfo(name = "TicketID")
    val ticketID: String,

    @ColumnInfo(name = "Logged_efforts")
    val efforts: Int = 0,

    @ColumnInfo(name = "StatusID")
    val StatusID: String,

    @ColumnInfo(name = "EffortDate")
    val effortDate: String,

    @ColumnInfo(name = "ServiceID")
    val serviceID: String,

    @ColumnInfo(name = "ActivityID")
    val activityID: String
)