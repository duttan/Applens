package com.example.applens.Database

import androidx.annotation.WorkerThread
import com.example.applens.createticket.Efforts
import kotlinx.coroutines.*


class TimesheetRepository(private val timesheetDao: TimesheetDao){


    private var timeSheetsForDate: List<Efforts> = emptyList()
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    //   val allTickets: LiveData<List<TimeSheet>> = timesheetDao.gettimesheet()


    fun insert(timesheet: TimeSheet) {
        uiScope.launch {
            insertSheet(timesheet)
        }
    }

    suspend fun insertSheet(timesheet: TimeSheet) {
        withContext(Dispatchers.IO) {
            timesheetDao.insert(timesheet)
        }
    }



    fun update(timesheet: TimeSheet) {
        uiScope.launch {
           updateSheet(timesheet)
        }
    }

    suspend fun updateSheet(timesheet: TimeSheet) {
        withContext(Dispatchers.IO) {
            timesheetDao.update(timesheet)
        }
    }










    fun getT(date: String): List<Efforts> {
        uiScope.launch {
            getTimeSheet(date)

        }
        return timeSheetsForDate
    }


    suspend fun getTimeSheet(date: String) {
        withContext(Dispatchers.IO) {

            timeSheetsForDate = timesheetDao.getTimesheet(date); return@withContext
        }


    }


}