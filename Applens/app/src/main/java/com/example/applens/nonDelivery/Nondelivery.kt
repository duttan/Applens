package com.example.applens.nonDelivery

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.applens.R

class Nondelivery : Fragment() {

    companion object {
        fun newInstance() = Nondelivery()
    }

    private lateinit var viewModel: NondeliveryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.non_delivery_activity, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NondeliveryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
