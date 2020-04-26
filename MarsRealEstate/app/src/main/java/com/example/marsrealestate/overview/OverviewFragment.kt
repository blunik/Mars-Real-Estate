@file:Suppress("DEPRECATION")

package com.example.marsrealestate.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.*
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import androidx.lifecycle.ViewModelProviders
import com.example.marsrealestate.R
import com.example.marsrealestate.databinding.FragmentOverviewBinding
import com.example.marsrealestate.databinding.GridViewItemBinding

class OverviewFragment: Fragment() {

   private val viewModel: OverviewViewModel by lazy {
        ViewModelProviders.of(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}