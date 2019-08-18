package com.example.applens.pushnotification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.applens.R

class ResultActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.getBooleanExtra("notification", false)) { //Just for confirmation
//            txtTitleView.text = intent.getStringExtra("title")
//            txtMsgView.text = intent.getStringExtra("message")

        }


    }

}