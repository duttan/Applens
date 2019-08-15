package com.example.applens.createticket


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applens.Database.Ticket
import com.example.applens.R





class TicketlistAdapter: RecyclerView.Adapter<TicketlistAdapter.ViewHolder>() {

    var data =  listOf<Ticket>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
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




        fun bind(item: Ticket) {
            val res = itemView.context.resources
            ticketid.text = item.ticket_Id
            ticketdesc.text = item.ticket_Desc
            ticketstatus.setSelection(getIndex(ticketstatus,item.ticket_Status))



        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_ticket_view, parent, false)

                return ViewHolder(view)
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
    }


}