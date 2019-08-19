package com.example.applens.searchTicket

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.applens.Database.ApplensDatabase

import com.example.applens.R
import com.example.applens.createticket.MainViewModel
import com.example.applens.createticket.MainViewModelFactory
import kotlinx.android.synthetic.main.first_page.*

class Search : Fragment() {

    companion object {
        fun newInstance() = Search()
    }

    private lateinit var searchviewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val view = inflater.inflate(R.layout.search_ticket, container, false)



        val application = requireNotNull(this.activity).application
        val dataSource = ApplensDatabase.getInstance(application).applensDatabaseDao
        val viewModelFactory = SearchViewModelFactory(dataSource,application)
        searchviewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)

        var search = view.findViewById<Button>(R.id.searching_button)
        var recyclerview = view.findViewById<RecyclerView>(R.id.recyclerView_searchlist)
        var filter1 = view.findViewById<Spinner>(R.id.spinner_application)
        var filter2 = view.findViewById<Spinner>(R.id.spinner_activity)
        var id = view.findViewById<EditText>(R.id.ticket_id)
        var status = view.findViewById<Spinner>(R.id.spinner_status)

        var filterone: String = ""
        var filtertwo: String = ""
        var filter: String = ""





        val adapter = SearchlistAdapter()

        search.setOnClickListener {


            if((filter1.selectedItem.toString().equals("Not sure")) && (!filter2.selectedItem.toString().equals("Not sure")))
            {
                filtertwo = filter2.selectedItem.toString()
            }
            else if((filter2.selectedItem.toString().equals("Not sure")) && (!filter1.selectedItem.toString().equals("Not sure")))
            {
                filterone = filter1.selectedItem.toString()
            }
            else if(!(filter2.selectedItem.toString().equals("Not sure")) && !(filter1.selectedItem.toString().equals("Not sure")))
            {
                filter = "both"
            }
            else
            {
                filter = "none"
            }


            if((filter1.selectedItem.toString().equals("Not sure")) && (!filter2.selectedItem.toString().equals("Not sure")))
            {
                filtertwo = filter2.selectedItem.toString()
            }
            else if((filter2.selectedItem.toString().equals("Not sure")) && (!filter1.selectedItem.toString().equals("Not sure")))
            {
                filterone = filter1.selectedItem.toString()
            }
            else if((!filter2.selectedItem.toString().equals("Not sure")) && (!filter1.selectedItem.toString().equals("Not sure")))
            {
                filter = "both"
            }
            else
            {
                //filter = "none"
            }

            if(filter.equals("both"))
            {
                searchviewModel.filterTickets2(filter1.selectedItem.toString(),filter2.selectedItem.toString()).observe(viewLifecycleOwner, Observer {
                    it?.let {
                        adapter.data = it
                    } })
            }
            else if(filterone.equals(filter1.selectedItem.toString()))
            {
                searchviewModel.filterTickets11(filterone).observe(viewLifecycleOwner, Observer {
                    it?.let {
                        adapter.data = it
                    } })
            }
            else if(filtertwo.equals(filter2.selectedItem.toString()))
            {
                searchviewModel.filterTickets12(filtertwo).observe(viewLifecycleOwner, Observer {
                    it?.let {
                        adapter.data = it
                    } })
            }
            else
            {
                searchviewModel.filterTickets3(id.text.toString(),status.selectedItem.toString()).observe(viewLifecycleOwner, Observer {
                    it?.let {
                        adapter.data = it
                    } })

            }



            recyclerview.adapter = adapter
        }


        return view
    }





}
