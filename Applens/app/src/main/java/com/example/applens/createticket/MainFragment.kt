package com.example.applens.createticket

import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.applens.Database.ApplensDatabase

import com.example.applens.databinding.FirstPageBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.applens.R
import com.example.applens.pushnotification.NotificationUtils
import devs.mulham.horizontalcalendar.HorizontalCalendar
import devs.mulham.horizontalcalendar.HorizontalCalendarView
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener
import devs.mulham.horizontalcalendar.utils.Utils
import kotlinx.android.synthetic.main.first_page.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.TimeUnit


class MainFragment : Fragment() {


    private lateinit var viewModel: MainViewModel

    private lateinit var calendarView: HorizontalCalendarView

    private lateinit var button: Button

    private lateinit var recyclerView: RecyclerView

    private lateinit var sendval: String







    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.first_page, container, false)


        calendarView = view.run { findViewById(R.id.calendarView1) }
        button = view.run { findViewById(R.id.fab) }
        recyclerView = view.findViewById(R.id.recyclerView_ticketlist)

        var formatter = SimpleDateFormat("YYYY-MM-dd")



        val application = requireNotNull(this.activity).application



        val dataSource = ApplensDatabase.getInstance(application).applensDatabaseDao
        val viewModelFactory = MainViewModelFactory(dataSource,application)
        val mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)



        val startDate = Calendar.getInstance()
        startDate.add(Calendar.MONTH, -1)

        val endDate = Calendar.getInstance()
        endDate.add(Calendar.MONTH, 1)

        val today = Calendar.getInstance()
        today.add(Calendar.DAY_OF_MONTH,1)





        val horizontalCalendar = HorizontalCalendar.Builder(view, R.id.calendarView1)
            .range(today, endDate)
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

        horizontalCalendar.calendarListener = object : HorizontalCalendarListener() {
            override fun onDateSelected(date: Calendar, position: Int) {
                date.add(Calendar.DAY_OF_MONTH,1)

                val adapter = TicketlistAdapter()
               // mainViewModel.retriveTickets(formatter.format(date.time).toString())

                var longi = endDate.timeInMillis - today.timeInMillis
                val difff:Long = TimeUnit.MILLISECONDS.toDays(longi)


                sendval = formatter.format(date.time).toString()
              //  Log.i("@@",sendval)

                mainViewModel.retriveTickets(formatter.format(date.time).toString()).observe(viewLifecycleOwner, Observer {
                    it?.let {
                        adapter.data = it

                        for(item in it) {
                            Log.i("@@", item.toString())
                        }

                        if (adapter.data.size > 0)
                        {
                            recyclerView.visibility = (View.VISIBLE)
                            empty_text.visibility = (View.INVISIBLE)
                        }
                        else
                        {
                            recyclerView.visibility = (View.INVISIBLE)
                            empty_text.visibility = (View.VISIBLE)
                        }
                    } })

                recyclerView.adapter = adapter

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



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
