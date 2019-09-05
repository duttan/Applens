package com.example.applens.Database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.applens.createticket.Efforts
import com.example.applens.createticket.Report

@Dao
interface TimesheetDao {



    @Query("select t1.ticket_Id,t1.ticketDesc,t2.TimeSheetID, t2.Logged_efforts ,t2.statusID,t2.effortDate,t2.serviceID,t2.activityID from Ticket_Table t1 left join timesheet_table t2 on t1.ticket_Id =t2.ticketID and t2.effortDate= :key WHERE strftime('%Y/%m/%d',t1.ticketOpenDate) <= strftime('%Y/%m/%d',t2.effortDate) and strftime('%Y/%m/%d',t1.ticketCloseDate) >= strftime('%Y/%m/%d',t2.effortDate) and t1.ticketStatus != 'Closed'")
    fun getTimesheet(key: String): List<Efforts>

    @Query("select distinct(EffortDate),sum(Logged_efforts) as summedEfforts from timesheet_table where Logged_efforts>0 GROUP BY EffortDate ORDER BY EffortDate ASC")
    fun getreport() :List<Report>

    @Query("select t1.ticket_Id,t1.ticketDesc,t2.TimeSheetID, t2.Logged_efforts ,t2.statusID,t2.effortDate,t2.serviceID,t2.activityID from Ticket_Table t1 left join timesheet_table t2 on t1.ticket_Id =t2.ticketID and t2.effortDate= :key WHERE strftime('%Y/%m/%d',t1.ticketOpenDate) <= strftime('%Y/%m/%d',t2.effortDate) and strftime('%Y/%m/%d',t1.ticketCloseDate) >= strftime('%Y/%m/%d',t2.effortDate) and t1.ticketStatus != 'Closed'")
    fun getTimesheet1(key: String): LiveData<List<Efforts>>

    @Insert
    fun insert(timeSheet: TimeSheet)

    @Query("UPDATE timesheet_table SET Logged_efforts = :eff, StatusID = :sta, ServiceID = :ser, ActivityId = :act WHERE TimeSheetID = :key")
    fun update1(eff:Int,sta:String,ser:String,act:String, key:String)

    @Update
    fun update(timeSheet: TimeSheet)

    @Query("Delete from timesheet_table")
    fun deleteAll()
}