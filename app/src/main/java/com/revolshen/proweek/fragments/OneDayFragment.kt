package com.revolshen.proweek.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.revolshen.proweek.R
import com.revolshen.proweek.adapters.RecyclerAdapter
import kotlinx.android.synthetic.main.one_day_fragment.*



class OneDayFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.one_day_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()

        recyclerView.layoutManager = GridLayoutManager(requireContext(),1)
        recyclerView.adapter = RecyclerAdapter(requireContext())


    }

}
