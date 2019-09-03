package com.example.applens.Database

import androidx.room.*
import com.example.applens.createticket.Efforts

@Dao
interface TimesheetDao {



    @Query("select t1.ticket_Id,t1.ticketDesc, t2.TimeSheetID, t2.Logged_efforts ,t2.statusID,t2.effortDate,t2.serviceID,t2.activityID from Ticket_Table t1 left join timesheet_table t2 on t1.ticket_Id =t2.ticketID and t2.effortDate= :date")
    fun getTimesheet(date: String): List<Efforts>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(timeSheet: TimeSheet)


    @Update
    fun update(timeSheet: TimeSheet)

    @Query("Delete from timesheet_table")
    fun deleteAll()
}