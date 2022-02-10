package com.sammuiga.emergram.ui.activities

import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sammuiga.emergram.R
import com.sammuiga.emergram.activities.ui.fragments.ContactsFragment
import com.sammuiga.emergram.activities.ui.fragments.MapsFragment
import com.sammuiga.emergram.activities.ui.fragments.ProfileFragment

class DashboardActivity : BaseActivity() {

    lateinit var contactsFragment: ContactsFragment
    lateinit var profileFragment: ProfileFragment
    lateinit var mapsFragment: MapsFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        var bottomnav = findViewById<BottomNavigationView>(R.id.BottomNavMenu)
        var frame = findViewById<FrameLayout>(R.id.frameLayout)

        contactsFragment = ContactsFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout, contactsFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()


        @Suppress("DEPRECATION")
        bottomnav.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.contacts -> {
                    contactsFragment = ContactsFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, contactsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.Map -> {
                    mapsFragment = MapsFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, mapsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                R.id.profile -> {
                    profileFragment = ProfileFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, profileFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

            }

            true
        }
    }
}
