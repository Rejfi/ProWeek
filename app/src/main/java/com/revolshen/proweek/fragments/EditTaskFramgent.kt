package com.revolshen.proweek.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.revolshen.proweek.R

class EditTaskFramgent : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        inflater.inflate(R.layout.edit_task_fragment, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}