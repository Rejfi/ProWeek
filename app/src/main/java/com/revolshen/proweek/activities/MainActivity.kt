package com.revolshen.proweek.activities

import android.os.Bundle
import com.github.clans.fab.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.revolshen.proweek.R
import com.revolshen.proweek.adapters.PagerAdapter
import com.revolshen.proweek.adapters.RecyclerAdapter
import com.revolshen.proweek.data.Task
import com.revolshen.proweek.viewmodels.TaskViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

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


