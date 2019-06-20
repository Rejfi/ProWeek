package com.revolshen.proweek.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.revolshen.proweek.fragments.*

class PagerAdapter(fm: FragmentManager, private val numberFrags: Int) : FragmentStatePagerAdapter(fm){
    override fun getItem(position: Int): Fragment? {
        return when(position){
            0 -> MondayFrag()
            1 -> TuesdayFrag()
            2 -> WednesdayFrag()
            3 -> ThursdayFrag()
            4 -> FridayFrag()
            5 -> SaturdayFrag()
            6 -> SundayFrag()
            else -> null
        }
    }

    override fun getCount(): Int {
        return numberFrags
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "Monday"
            1 -> return "Tuesday"
            2 -> return "Wednesday"
            3 -> return "Thursday"
            4 -> return "Friday"
            5 -> return "Saturday"
            6 -> return "Sunday"
        }


        return super.getPageTitle(position)


    }
}