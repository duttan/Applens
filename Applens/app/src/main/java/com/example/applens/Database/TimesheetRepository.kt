package com.example.applens.Database

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.applens.createticket.Efforts
import com.example.applens.createticket.Report
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.Exception


class TimesheetRepository(private val timesheetDao: TimesheetDao) {


    var formatter = SimpleDateFormat("YYYY-MM-dd")

    private var timeSheetsForDate: List<Efforts> = emptyList()
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var today = Calendar.getInstance()

    val date = formatter.format(today.time).toString()

//    init {
//        timeSheetsForDate = getT(date)
//    }


    //   val allTickets: LiveData<List<TimeSheet>> = timesheetDao.gettimesheet()


    @Throws(Exception::class)
    fun insertupdate(timesheet: TimeSheet, eff: Int, sta: String, ser: String, act: String, key: String) {


        uiScope.launch {
            try {
                insertSheet(timesheet)
            } catch (e: Exception) {
                // update1(eff,sta,ser,act,key)
            }
            return@launch
        }


    }

    @Throws(Exception::class)
    fun insertupdate1(timesheet: TimeSheet, eff: Int, sta: String, ser: String, act: String, key: String) {


        uiScope.launch {
            try {
                insertSheet(timesheet)
            } catch (e: Exception) {
                update1(eff, sta, ser, act, key)
            }
            return@launch
        }


    }

    @Throws(Exception::class)
    suspend fun insertSheet(timesheet: TimeSheet) {
        withContext(Dispatchers.IO) {
            timesheetDao.insert(timesheet)
            return@withContext
        }
    }


    fun update(timesheet: TimeSheet) {
        uiScope.launch {
            updateSheet(timesheet)
            return@launch
        }
    }

    suspend fun updateSheet(timesheet: TimeSheet) {
        withContext(Dispatchers.IO) {
            timesheetDao.update(timesheet)
            return@withContext
        }
    }


    fun update1(eff: Int, sta: String, ser: String, act: String, key: String) {
        uiScope.launch {
            updateSheet1(eff, sta, ser, act, key)
            return@launch

        }
    }

    suspend fun updateSheet1(eff: Int, sta: String, ser: String, act: String, key: String) {
        withContext(Dispatchers.IO) {
            timesheetDao.update1(eff, sta, ser, act, key)
            return@withContext
        }
    }


    fun fetchReport(): List<Report> {
        return timesheetDao.getreport()
    }


    fun getT(date: String): List<Efforts> {

        timeSheetsForDate = timesheetDao.getTimesheet(date)
        return timeSheetsForDate

    }

}

//
//    fun getT(date: String): LiveData<List<Efforts>> {
//        runBlocking {  getTimeSheet(date) }
//
//        return timeSheetsForDate
//    }
//
//    suspend fun getTimeSheet(date: String):LiveData<List<Efforts>> {
//        withContext(Dispatchers.IO) {
//            timeSheetsForDate = async { timesheetDao.getTimesheet1(date) }.await()
//        }
//
//        return timeSheetsForDate
//    }
//
//}



//        timeSheetsForDate = timesheetDao.getTimesheet(date)
//        return timeSheetsForDate



