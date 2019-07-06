package com.revolshen.proweek.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.revolshen.proweek.R
import com.revolshen.proweek.adapters.PagerAdapter
import com.revolshen.proweek.data.Task
import com.revolshen.proweek.fragments.EditTaskFragment
import com.revolshen.proweek.fragments.MyTaskFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fm = supportFragmentManager
        viewPager.adapter = PagerAdapter(fm, 2)

        tabLayout = findViewById(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager, true)

    }

}


