package com.example.nguyenduchuy.activity.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.example.nguyenduchuy.R
import com.example.nguyenduchuy.databinding.ActivityMainBinding
import com.example.nguyenduchuy.fragment.nowplaying.view.NowPlayingFragment
import com.example.nguyenduchuy.fragment.popular.view.PopularFragment
import com.example.nguyenduchuy.fragment.search.view.SearchFragment
import com.example.nguyenduchuy.fragment.toprated.view.TopRatedFragment
import com.example.nguyenduchuy.fragment.upcoming.view.UpcomingFragment
import com.example.nguyenduchuy.util.Utils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val manager: FragmentManager = supportFragmentManager

        Utils.addFragment(manager, R.id.fl_Main_Container,
            UpcomingFragment.newInstance(), false)


        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.upcoming ->{
                    Utils.addFragment(manager, R.id.fl_Main_Container,
                        UpcomingFragment.newInstance(), false)
                    true
                }
                R.id.topRated ->{
                    Utils.addFragment(manager, R.id.fl_Main_Container,
                        TopRatedFragment.newInstance(), false)
                    true
                }
                R.id.popular ->{
                    Utils.addFragment(manager, R.id.fl_Main_Container,
                        PopularFragment.newInstance(), false)
                    true
                }
                R.id.nowPlaying ->{
                    Utils.addFragment(manager, R.id.fl_Main_Container,
                        NowPlayingFragment.newInstance(), false)
                    true
                }
                else -> false
            }
        }

        binding.tvSearch.setOnClickListener {
            Utils.addFragment(manager, R.id.fl_Main_Container,
                SearchFragment.newInstance(), true)
        }

        supportFragmentManager.addOnBackStackChangedListener {
            if(supportFragmentManager.backStackEntryCount == 0){
                binding.tvSearch.visibility = View.VISIBLE
                binding.bottomNavigation.visibility = View.VISIBLE
            } else {
                binding.bottomNavigation.visibility = View.GONE
                binding.tvSearch.visibility = View.GONE
            }
        }
    }

}