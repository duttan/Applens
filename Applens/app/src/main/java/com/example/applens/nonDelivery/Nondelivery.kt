package com.example.applens.nonDelivery

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import com.example.applens.R




class Nondelivery : Fragment() {

    companion object {
        fun newInstance() = Nondelivery()
    }

    private lateinit var viewModel: NondeliveryViewModel
    private lateinit var okbut: TextView
    private lateinit var remarks:EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.non_delivery_activity, container, false)

        okbut = view.findViewById<TextView>(R.id.okbutton)
        remarks = view.findViewById(R.id.remarks)



        okbut.setOnClickListener {

            Toast.makeText(activity!!.applicationContext,"Saved Successfully",Toast.LENGTH_SHORT).show()
            activity?.onBackPressed()


        }



        return view
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NondeliveryViewModel::class.java)
        // TODO: Use the ViewModel
    }



}
