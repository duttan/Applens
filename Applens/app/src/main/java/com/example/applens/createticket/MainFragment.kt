package com.example.applens.createticket

import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.applens.Database.ApplensDatabase
import com.example.applens.Database.Ticket


import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.applens.R
import com.example.applens.pushnotification.NotificationUtils
import com.wang.avi.AVLoadingIndicatorView
import devs.mulham.horizontalcalendar.HorizontalCalendar
import devs.mulham.horizontalcalendar.HorizontalCalendarView
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener
import devs.mulham.horizontalcalendar.utils.Utils
import kotlinx.android.synthetic.main.first_page.*
import kotlinx.coroutines.runBlocking
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule


class MainFragment : Fragment() {



    private lateinit var viewModel: MainViewModel

    private lateinit var mainViewModel: MainViewModel

    private lateinit var calendarView: HorizontalCalendarView

    private lateinit var button: Button

    private lateinit var recyclerView: RecyclerView

    private lateinit var sendval: String

    private lateinit var submitbutton:Button

    private lateinit var thisadapter: TicketlistAdapter

    private lateinit var emptytext: TextView

    private lateinit var anim:AVLoadingIndicatorView




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.first_page, container, false)



        calendarView = view.run { findViewById(R.id.calendarView1) }
        button = view.run { findViewById(R.id.fab) }
        recyclerView = view.findViewById(R.id.recyclerView_ticketlist)
        submitbutton = view.findViewById(R.id.submitbutton)
        var effortdisplay = view.findViewById<TextView>(R.id.effort_display)
        emptytext = view.findViewById(R.id.empty_text)
        anim = view.findViewById(R.id.indicator)

        submitbutton.isEnabled = true

        var formatter = SimpleDateFormat("YYYY-MM-dd")





        val application = requireNotNull(this.activity).application
        val dataSource = ApplensDatabase.getInstance(application).applensDatabaseDao
        val viewModelFactory = MainViewModelFactory(dataSource,application)
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)



        val startDate = Calendar.getInstance()
        startDate.add(Calendar.MONTH, -1)

        val endDate = Calendar.getInstance()
        endDate.add(Calendar.MONTH, 1)

        val today = Calendar.getInstance()
        today.add(Calendar.DAY_OF_MONTH,0)



        val horizontalCalendar = HorizontalCalendar.Builder(view, R.id.calendarView1)
            .range(startDate, today)
            .datesNumberOnScreen(5)
            .configure()    // starts configuration.
            .formatTopText("MM")       // default to "MMM".
            .formatMiddleText("EEE")    // default to "dd".
            .formatBottomText("dd")    // default to "EEE".
            .showTopText(true)              // show or hide TopText (default to true).
            .showBottomText(true)           // show or hide BottomText (default to true).
            .textColor(Color.DKGRAY, Color.WHITE)
            // default to (Color.LTGRAY, Color.WHITE).
            // set selected date cell background.
            .selectorColor(Color.BLACK)
            .end()          // ends configuration.
            .defaultSelectedDate(today)
            .build()

       // Log.i("@@",today.time.toString())

        var immediate = true
        horizontalCalendar.goToday(immediate)

        thisadapter = TicketlistAdapter(activity!!.applicationContext,mainViewModel)


        sendval = formatter.format(today.time).toString()
        load_recyclerview()
        anim.hide()



//        var cache:List<Ticket> = emptyList()
//
//        fun setcache(tlist:List<Ticket>)
//        {
//            cache = tlist
//        }
//
//        fun refresh()
//        {
//            mainViewModel.firstEffortLog(cache,sendval)
//        }



        horizontalCalendar.calendarListener = object : HorizontalCalendarListener() {
            override fun onDateSelected(date: Calendar, position: Int) {
                date.add(Calendar.DAY_OF_MONTH,1)

                sendval = formatter.format(date.time).toString()
                effortdisplay.text = "0 Hrs"



                mainViewModel.retriveTickets(formatter.format(date.time).toString()).observe(viewLifecycleOwner, Observer {
                    it?.let {
                        mainViewModel.firstEffortLog(it, sendval)
                    }

                })

               for( item in mainViewModel.getReportStatus())
               {
                   if(item.EffortDate.equals(sendval))
                   {
                       effortdisplay.text = item.summedEfforts+" Hrs"
                   }

               }

                // var list:List<Efforts> = mainViewModel.getTicketsForDate(sendval)
               // Log.i("@@",list.toString())

                anim.show()
                Handler().postDelayed({
                    anim.hide()
                    load_recyclerview()
                }, 1500)




                submitbutton.setOnClickListener {
                    var totalEffort : Int = 0
                    for (item in thisadapter.getUpdatedEfforts()){
                       totalEffort =  totalEffort+item.Logged_efforts
                    }
                    if(totalEffort > 8){


                        val builder = AlertDialog.Builder(activity!!)

                        builder.setTitle("Note")
                        builder.setMessage("You Cannot log more than 8 hours per day!")
                        builder.setIcon(R.drawable.baseline_warning_black_24)

                        builder.setNeutralButton("Ok"){dialogInterface , which ->
                            dialogInterface.dismiss()
                        }

                        val alertDialog: AlertDialog = builder.create()
                        alertDialog.setCancelable(false)
                        alertDialog.show()



                    }else {
                        effortdisplay.text = totalEffort.toString() + " Hrs"
                        //mainViewModel.bulkInsertUpdateEfforts(adapter.getUpdatedEfforts(), sendval)


                        val builder = AlertDialog.Builder(activity!!)

                        builder.setTitle("Submit")
                        builder.setMessage("Are you sure to submit timesheet for the day - " + sendval + "?")
                        builder.setIcon(R.drawable.baseline_warning_black_24)
                        builder.setPositiveButton("Yes") { dialogInterface, which ->
                            mainViewModel.bulkInsertUpdateEfforts(thisadapter.getUpdatedEfforts(), sendval)
                            Toast.makeText(context,"Submitted Successfully",Toast.LENGTH_SHORT).show()
                        }
                        builder.setNeutralButton("Cancel") { dialogInterface, which ->
                            dialogInterface.dismiss()
                        }

                        val alertDialog: AlertDialog = builder.create()
                        alertDialog.setCancelable(false)
                        alertDialog.show()
                    }

                }

            }

            override fun onCalendarScroll(calendarView: HorizontalCalendarView?, dx: Int, dy: Int)
            {

            }

            override fun onDateLongClicked(date: Calendar, position: Int): Boolean {
                return true
            }
        }


        horizontalCalendar.refresh()


        button.setOnClickListener {

            val createTicketFragment = CreateTicketFragment()
            var args = Bundle()
            args.putString("MyDateKey", sendval)
            createTicketFragment.setArguments(args)

            val transaction = fragmentManager!!.beginTransaction()
            transaction.add(R.id.root_layout,createTicketFragment).addToBackStack(null).commit()

        }


        return view
    }

    fun load_recyclerview()
    {
        mainViewModel.getTicketsForDate(sendval).observe(viewLifecycleOwner, Observer {
            it?.let {

                // thisadapter.setEfforts(it)
                thisadapter.efforts = it

                for (item in it) {
                    Log.i("@@", item.toString())
                }

                if (it.size > 0) {

                    recyclerView.visibility = (View.VISIBLE)
                    empty_text.visibility = (View.INVISIBLE)
                } else {
                    recyclerView.visibility = (View.INVISIBLE)
                    empty_text.visibility = (View.VISIBLE)
                }
            }
        })


        recyclerView.adapter = thisadapter

    }



//
//    fun load_recyclerview()
//    {
//        thisadapter.setEfforts(mainViewModel.getTicketsForDate(sendval))
//
//        if(mainViewModel.getTicketsForDate(sendval).size>0) {
//
//            recyclerView.visibility = (View.VISIBLE)
//            emptytext.visibility = (View.INVISIBLE)
//        }
//
//        else
//        {
//            recyclerView.visibility = (View.INVISIBLE)
//            emptytext.visibility = (View.VISIBLE)
//        }
//
//
//        recyclerView.adapter = thisadapter
//    }


    override fun onPause() {
        super.onPause()
        load_recyclerview()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
