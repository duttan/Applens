package com.example.applens.createticket

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.example.applens.Database.*
import kotlinx.coroutines.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(val database: ApplensDatabaseDao, application: Application): AndroidViewModel(application)
{

    var int:Int = 0
    // TODO: Implement the ViewModel
    private var viewModelJob = Job()



    // var alltickets = database.getAllTickets()
    val allTicket: LiveData<List<Ticket>>

    val timeSheetDao = ApplensDatabase.getInstance(application).timesheetDao
    private var repository: TicketRepository
    private var repoTimesheet: TimesheetRepository




    init {
        val timeSheetDao = ApplensDatabase.getInstance(application).timesheetDao

        repository = TicketRepository(database)
        repoTimesheet = TimesheetRepository(timeSheetDao)
        allTicket = repository.allTickets
        //allEfforts = repoTimesheet.getTimeSheet("date1")

        }

    var dateFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)

    lateinit var alltickets: LiveData<List<Ticket>>

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun retriveEfforts(date_str: String): LiveData<List<Efforts>>{

        return timeSheetDao.getTimesheet1(date_str)
       // return repoTimesheet.getT(date_str)
         }

    fun retriveTickets(date_str: String): LiveData<List<Ticket>>{

        return database.getTickets(date_str) }



//
//    fun bulkInsertUpdateEfforts(efforts:List<Efforts>) = uiScope.launch {
//        for(effort in efforts) {
//            try {
//                var timeSheet = TimeSheet(effort.TicketID+"date1",effort.TicketID!!,effort.Logged_efforts,effort.StatusID!!,"date1",effort.serviceID!!,effort.activityID!!)
//               // repoTimesheet.insert(timeSheet)
//                }
//
//            catch (e: Exception ){
//                var timeSheet = TimeSheet(effort.TimeSheetID,effort.TicketID!!,effort.Logged_efforts,effort.StatusID!!,"date1",effort.serviceID!!,effort.activityID!!)
//                repoTimesheet.update(timeSheet)
//            }
//
//        }
//    }

    fun firstEffortLog(tickets: List<Ticket>,date_str: String) = uiScope.launch{


        for(ticket in tickets) {


            try {
                var timeSheet = TimeSheet(ticket.ticket_Id+date_str,ticket.ticket_Id,ticket.ticket_Effort,ticket.ticket_Status,date_str,ticket.ticket_Service,ticket.ticket_Activity)
                repoTimesheet.insertupdate(timeSheet,ticket.ticket_Effort,ticket.ticket_Status,ticket.ticket_Service,ticket.ticket_Activity,ticket.ticket_Id+date_str)

            }
            catch (e: Exception ){

                // var timeSheet = TimeSheet(ticket.ticket_Id+date_str,ticket.ticket_Id!!,ticket.ticket_Effort,ticket.ticket_Status,date_str,ticket.ticket_Service,ticket.ticket_Activity)
                //repoTimesheet.update1(ticket.ticket_Effort,ticket.ticket_Status,ticket.ticket_Service,ticket.ticket_Activity,ticket.ticket_Id+date_str)
                //  repoTimesheet.update(timeSheet)

            }

        }
    }






    fun getTicketsForDate(date_str: String) : List<Efforts> {
        return repoTimesheet.getT(date_str)

    }



    private suspend fun getTickets() {
        withContext(Dispatchers.IO) {



        }

    }
}
