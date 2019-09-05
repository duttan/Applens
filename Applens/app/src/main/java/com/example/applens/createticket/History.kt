package com.example.applens.createticket

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.applens.Database.ApplensDatabase
import com.example.applens.R

class History:AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_activity)
        supportActionBar!!.setTitle(R.string.timesheet)

        val application = requireNotNull(this).application
        val dataSource = ApplensDatabase.getInstance(application).applensDatabaseDao
        val viewModelFactory = MainViewModelFactory(dataSource,application)
        val historyViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)


        var recyclerView = findViewById<RecyclerView>(R.id.history_recycler)

        val adapternew = ReportAdapter(this)

        adapternew.setReportStatus(historyViewModel.getReportStatus())

        recyclerView.adapter = adapternew
    }
}