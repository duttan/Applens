package com.example.applens

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.applens.createticket.CreateTicketFragment
import com.example.applens.createticket.MainFragment
import com.example.applens.nonDelivery.Nondelivery
import com.example.applens.pushnotification.NotificationUtils
import com.example.applens.searchTicket.Search
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val mNotificationTime = Calendar.getInstance().timeInMillis + 5000 //Set after 5 seconds from the current time.

    private var mNotified = false


    //Test migration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (!mNotified) {
            NotificationUtils().setNotification(mNotificationTime, this@MainActivity)
        }


       val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction().add(R.id.base_layout,MainFragment(),"").commit()

//        val fab: Button = findViewById(R.id.fab)
//        fab.setOnClickListener {
//            supportFragmentManager.beginTransaction().add(R.id.root_layout,CreateTicketFragment(),"").commit()
//        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_search_ticket -> {

                val searchFragment:Fragment = Search.newInstance()
                val transaction = supportFragmentManager!!.beginTransaction()
                transaction.replace(R.id.root_layout,searchFragment).addToBackStack(null).commit()
            }

            R.id.nav_add_non_delivery_activity -> {

                val nonDFragment:Fragment = Nondelivery.newInstance()
                val transaction = supportFragmentManager!!.beginTransaction()
                transaction.replace(R.id.root_layout,nonDFragment).addToBackStack(null).commit()

            }


        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
