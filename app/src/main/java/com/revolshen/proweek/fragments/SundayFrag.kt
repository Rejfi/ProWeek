package com.revolshen.proweek.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.RecyclerView

import com.revolshen.proweek.R
import com.revolshen.proweek.adapters.RecyclerAdapter

class SundayFrag : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RecyclerAdapter
    lateinit var viewModel: AndroidViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.one_day_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()

        recyclerView = view!!.findViewById<RecyclerView>(R.id.recyclerView)
    }
}
