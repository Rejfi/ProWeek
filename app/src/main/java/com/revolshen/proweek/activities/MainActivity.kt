package com.revolshen.proweek.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.revolshen.proweek.fragments.OneDayFragment
import com.revolshen.proweek.R

class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tabLayout)

        val fm = supportFragmentManager
        val oneDayFragment = OneDayFragment()
        fm.beginTransaction().add(R.id.fragment_container, oneDayFragment).commit()


    }



}


