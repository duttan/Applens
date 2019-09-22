package com.example.applens.createticket


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applens.Database.Ticket
import com.example.applens.R

class TicketlistAdapter internal constructor(context: Context, mainViewModel: MainViewModel): RecyclerView.Adapter<TicketlistAdapter.ViewHolder>() {

     var tickets = emptyList<Ticket>()
    // var efforts = emptyList<Efforts>()
     var count:Int = 0

    var efforts =  listOf<Efforts>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    var mcontext: Context? = null
    var mmainViewModel: MainViewModel? = null


    init {
        mcontext = context
        mmainViewModel = mainViewModel
    }



    override fun getItemCount() = efforts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = efforts[position]

        //holder.bind(item,position)

        holder.ticketid.text = item.ticket_Id
        holder.ticketdesc.text = item.ticketDesc
        holder.ticketstatus.setSelection(getIndex(holder.ticketstatus,item.ticketStatus))



        if(!efforts.isEmpty()) {

            val current = efforts[position]
            holder.ticketstatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    current.ticketStatus = holder.ticketstatus.selectedItem.toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    Log.e("@@", "onNothingSelected")
                }
            }

            if (current.ticketStatus != null) {
                holder.ticketstatus.setSelection(getIndex(holder.ticketstatus, current.ticketStatus))
            }


            holder.ticketservice.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    current.ServiceID = holder.ticketservice.selectedItem.toString()
//                tickets.get(position).statusID = listStatus.get(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    Log.e("@@", "onNothingSelcted")
                }
            }
            if (current.ServiceID != null) {
                holder.ticketservice.setSelection(getIndex(holder.ticketservice, current.ServiceID!!))
            }


            holder.ticketactivity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    current.ActivityID = holder.ticketactivity.selectedItem.toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    Log.e("@@", "onNothingSelcted")
                }
            }
            if (current.ActivityID != null) {
                holder.ticketactivity.setSelection(getIndex(holder.ticketactivity, current.ActivityID!!))
            }



            holder.img_plus.setOnClickListener {
                if(count < 8) {
                    count = item.Logged_efforts
                    count+=1
                    holder.effort.text = count.toString() + " Hrs"
                    current.Logged_efforts = count

                }
            }

            if(current.Logged_efforts >= 0)
            {
                holder.effort.text = current.Logged_efforts.toString()+ " Hrs"
            }

            holder.img_minus.setOnClickListener {

                if(count > 0) {
                    count = item.Logged_efforts
                    count-=1
                    holder.effort.text = count.toString() + " Hrs"
                    current.Logged_efforts = count
                }
            }

        }



    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){


        val ticketid: TextView = itemView.findViewById(R.id.ticket_id)
        val ticketdesc: TextView = itemView.findViewById(R.id.ticket_description)
        val ticketstatus: Spinner = itemView.findViewById(R.id.spinner_status)
        val ticketservice: Spinner = itemView.findViewById(R.id.spinner_service)
        val ticketactivity: Spinner = itemView.findViewById(R.id.spinner_activity)
        val img_plus:ImageView = itemView.findViewById(R.id.counter_img1)
        val effort:TextView = itemView.findViewById(R.id.effort_log)
        val img_minus:ImageView = itemView.findViewById(R.id.counter_img2)



        companion object {
            fun from(parent: ViewGroup): ViewHolder {

                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_ticket_view, parent, false)

                return ViewHolder(view)
            }
        }


    }

    private fun getIndex(spinner: Spinner, myString: String): Int {
        for (i in 0 until spinner.count) {
            if (spinner.getItemAtPosition(i).toString().equals(myString, ignoreCase = true)) {
                return i
            }
        }

        return 0
    }

    fun getUpdatedEfforts(): List<Efforts> {
        return efforts
    }

    internal fun setTickets(tickets: List<Ticket>) {
        this.tickets = tickets
        notifyDataSetChanged()
    }

    internal fun setEfforts(efforts: List<Efforts>) {
        this.efforts = efforts
        notifyDataSetChanged()
    }

    fun getUpdatedTickets(): List<Ticket> {
        return tickets
    }








}