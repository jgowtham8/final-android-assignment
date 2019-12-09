package com.example.movieshub.main.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.movieshub.R
import com.example.movieshub.main.adapters.HomePagerAdapter
import com.example.movieshub.main.fragments.FavouritesFragment
import com.example.movieshub.main.fragments.PopularFragment
import com.example.movieshub.main.fragments.SearchFragment
import com.example.movieshub.main.fragments.UpcomingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar_with_back.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setUpViewPager()

        bottomNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        bottomNavigationContainer.currentItem = 0

        bottomNavigation.setOnNavigationItemReselectedListener {
            return@setOnNavigationItemReselectedListener
        }
    }

    private fun setUpViewPager(){
        val adapter = HomePagerAdapter(this.supportFragmentManager)
        adapter.addFrag(PopularFragment.newInstance(), "Popular")
        adapter.addFrag(SearchFragment.newInstance(), "Search")
        adapter.addFrag(FavouritesFragment.newInstance(), "Favourites")
        adapter.addFrag(UpcomingFragment.newInstance(), "Upcoming")

        bottomNavigationContainer.adapter = adapter

        bottomNavigationContainer.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {}

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        bottomNavigation.selectedItemId = R.id.tabPopular
                    }
                    1 -> {
                        bottomNavigation.selectedItemId = R.id.tabSearch
                    }
                    2 -> {
                        bottomNavigation.selectedItemId = R.id.tabFavourites
                    }
                    3 -> {
                        bottomNavigation.selectedItemId = R.id.tabUpcoming
                    }
                }
            }
        })
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->


        when (item.itemId) {
            R.id.tabPopular -> {
//                toolbarTitle.text = getString(R.string.titleNotification)
//                BottomMenuHelper.removeBadge(bottomNavigation, R.id.navigationNotification)
                bottomNavigationContainer.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.tabSearch -> {
//                toolbarTitle.text = getString(R.string.titleProjects)
                bottomNavigationContainer.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.tabFavourites -> {
//                toolbarTitle.text = getString(R.string.titleInbox)
                bottomNavigationContainer.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
            R.id.tabUpcoming -> {
//                toolbarTitle.text = getString(R.string.titleWallet)
                bottomNavigationContainer.currentItem = 3
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
