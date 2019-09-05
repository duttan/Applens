package com.example.applens.pushnotification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.applens.MainActivity
import com.example.applens.createticket.Popupactivity

class AlarmReceiver:BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val changepage = Intent(context, Popupactivity::class.java)
        context.startActivity(changepage)

//        val service = Intent(context, NotificationService::class.java)
//        service.putExtra("reason", intent.getStringExtra("reason"))
//        service.putExtra("timestamp", intent.getLongExtra("timestamp", 0))
//
//
//        Toast.makeText(context,"Received",Toast.LENGTH_SHORT).show()
//
//
//        service.data = Uri.parse("custom://" + System.currentTimeMillis())
//        context.startService(service)




    }

}