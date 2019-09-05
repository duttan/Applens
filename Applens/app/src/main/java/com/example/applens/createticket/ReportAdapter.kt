package com.example.applens.createticket

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applens.R

class ReportAdapter internal constructor(context: Context) : RecyclerView.Adapter<ReportAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var report = emptyList<Report>() // Cached copy of words


    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateview: TextView = itemView.findViewById(R.id.date)
        val effortview: TextView = itemView.findViewById(R.id.effort)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.history_item_layout, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = report[position]
        holder.dateview.text = current.EffortDate

        if(current.summedEfforts.equals("8") or current.summedEfforts.equals("7")) {
            holder.effortview.setTextColor(Color.parseColor("#188034"))
            holder.effortview.text = current.summedEfforts + " Hrs"
        }
        else if(current.summedEfforts.equals("5") or current.summedEfforts.equals("6")) {
            holder.effortview.setTextColor(Color.parseColor("#e0af26"))
            holder.effortview.text = current.summedEfforts + " Hrs"
        }
        else
        {
            holder.effortview.setTextColor(Color.RED)
            holder.effortview.text = current.summedEfforts + " Hrs"

        }



    }

    internal fun setReportStatus(list: List<Report>) {
        this.report = list
        notifyDataSetChanged()
    }

    override fun getItemCount() = report.size
}