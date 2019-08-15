package com.example.applens

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Login: AppCompatActivity()
{

    private lateinit var login:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        var color = ColorDrawable(Color.parseColor("#2a2b2b"))
       supportActionBar!!.setBackgroundDrawable(color)

        var user = findViewById<EditText>(R.id.login_id)
        var pass = findViewById<EditText>(R.id.login_password)
        login = findViewById(R.id.login_button)


        login.setOnClickListener {
            validate(user.text.toString(),pass.text.toString())

    }

    }


    private fun validate(user:String,pass:String)
    {
        if ((user.equals("666299")) && (pass.equals("password"))) {
            val changepage = Intent(this, MainActivity::class.java)
            startActivity(changepage)
            finish()
        }
        else
        {
            Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show()
        }
    }



}