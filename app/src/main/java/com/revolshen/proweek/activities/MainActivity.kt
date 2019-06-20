package com.revolshen.proweek.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.revolshen.proweek.R
import com.revolshen.proweek.adapters.PagerAdapter

class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tabLayout)

        val fm = supportFragmentManager
        val pagerAdapter = PagerAdapter(fm, 7)

        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager, true)

    }



}


