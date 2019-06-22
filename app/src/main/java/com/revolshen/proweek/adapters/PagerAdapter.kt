package com.revolshen.proweek.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.revolshen.proweek.fragments.*

class PagerAdapter(fm: FragmentManager, private val numberFrags: Int) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getItem(position: Int): Fragment {
        val taskViewFragment = OneDayFragment()
        val taskCreatorFragment = OneDayFragment()

        return when(position){
            0 -> taskViewFragment
            1 -> taskCreatorFragment
            else -> taskViewFragment
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