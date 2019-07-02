package com.revolshen.proweek.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.revolshen.proweek.fragments.*

class PagerAdapter(private val fm: FragmentManager, private val numberFrags: Int) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){


    override fun getItem(position: Int): Fragment {
        val myTaskFragment = MyTaskFragment()
        val editTaskFragment = EditTaskFragment()
        return when(position){
            0 -> myTaskFragment
            1 -> editTaskFragment
            else -> myTaskFragment
        }
    }
    override fun getCount(): Int {
        return numberFrags
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "My Task"
            1 -> return "New Task"
        }

        return super.getPageTitle(position)
    }
}