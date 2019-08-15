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
import com.example.applens.Database.ApplensDatabase

import com.example.applens.databinding.FirstPageBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.applens.R
import devs.mulham.horizontalcalendar.HorizontalCalendar
import devs.mulham.horizontalcalendar.HorizontalCalendarView
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener
import devs.mulham.horizontalcalendar.utils.Utils
import kotlinx.android.synthetic.main.first_page.*
import java.util.*





class MainFragment : Fragment() {


    private lateinit var viewModel: MainViewModel

    private lateinit var calendarView: HorizontalCalendarView

    private lateinit var button: Button



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.first_page, container, false)

        calendarView = view.findViewById(R.id.calendarView1)
        button = view.findViewById(R.id.fab)


        val application = requireNotNull(this.activity).application



        val dataSource = ApplensDatabase.getInstance(application).applensDatabaseDao
        val viewModelFactory = MainViewModelFactory(dataSource,application)
        val mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)



        val startDate = Calendar.getInstance()
        startDate.add(Calendar.MONTH, -1)

        val endDate = Calendar.getInstance()
        endDate.add(Calendar.MONTH, 1)

        val today = Calendar.getInstance()
        today.add(Calendar.DAY_OF_WEEK,0)



        val horizontalCalendar = HorizontalCalendar.Builder(view, R.id.calendarView1)
            .range(today, endDate)
            .datesNumberOnScreen(5)
            .configure()    // starts configuration.
            .formatTopText("MM")       // default to "MMM".
            .formatMiddleText("EEE")    // default to "dd".
            .formatBottomText("dd")    // default to "EEE".
            .showTopText(true)              // show or hide TopText (default to true).
            .showBottomText(true)           // show or hide BottomText (default to true).
            .textColor(Color.DKGRAY, Color.WHITE)    // default to (Color.LTGRAY, Color.WHITE).
            // set selected date cell background.
            .selectorColor(Color.BLACK)
            .end()          // ends configuration.
            .defaultSelectedDate(today)
            .build()

        horizontalCalendar.calendarListener = object : HorizontalCalendarListener() {
            override fun onDateSelected(date: Calendar, position: Int) {



                if( Utils.isSameDate(date, Calendar.getInstance())) {


                }
                else
                {

                    Log.e("@@Date", date.time.toString())

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
            val transaction = fragmentManager!!.beginTransaction()
            transaction.add(R.id.root_layout,createTicketFragment).addToBackStack(null).commit()

        }

        //val adapter = TicketlistAdapter()
       // binding.recyclerViewTicketlist.adapter = adapter

        //mainViewModel.alltickets.observe(viewLifecycleOwner, Observer { it?.let { adapter.data = it } })

        //work on recycler view




        return view
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
