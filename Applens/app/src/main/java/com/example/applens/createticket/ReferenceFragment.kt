package com.example.applens.createticket

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.applens.R

class ReferenceFragment : Fragment() {

    companion object {
        fun newInstance() = ReferenceFragment()
    }

    private lateinit var viewModel: ReferenceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.reference_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ReferenceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
