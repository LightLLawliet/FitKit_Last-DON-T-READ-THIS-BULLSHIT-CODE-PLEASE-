package com.example.fitkit_last

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.fitkit_last.activities.LoginActivity
import com.example.fitkit_last.activities.SearchActivity
import com.example.fitkit_last.databinding.ActivityMainBinding
import com.example.fitkit_last.drawer_fragments.*
import com.example.fitkit_last.navigation_fragments.Chats
import com.example.fitkit_last.navigation_fragments.Home
import com.example.fitkit_last.navigation_fragments.Notifications
import com.example.fitkit_last.navigation_fragments.Profile
import com.example.fitkit_last.side_funcs.AUTH
import com.example.fitkit_last.side_funcs.initFirebase
import com.example.fitkit_last.side_funcs.replaceActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mDrawer: DrawerLayout
    private lateinit var mToolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        bottomNavigation()
        initFunc()
    }

    override fun onResume() {
        super.onResume()
        navStart()
        navSearch()
        userLogout()
        drawerItemListener()
    }

    private fun userLogout() {
        mBinding.logout.setOnClickListener {
            AUTH.signOut()
            replaceActivity(LoginActivity())
        }
    }

    private fun drawerItemListener() {
        mBinding.appDrawerHomeItem.setOnClickListener {
            replaceFragment(Home())
            mDrawer.closeDrawer(GravityCompat.START)
        }
        mBinding.appDrawerAboutItem.setOnClickListener {
            replaceFragment(AboutDrawerFragment())
            mDrawer.closeDrawer(GravityCompat.START)
        }
        mBinding.appDrawerExercisesItem.setOnClickListener {
            replaceFragment(ExercisesDrawerFragment())
            mDrawer.closeDrawer(GravityCompat.START)
        }
        mBinding.appDrawerPricesItem.setOnClickListener {
            replaceFragment(PricesDrawerFragment())
            mDrawer.closeDrawer(GravityCompat.START)
        }
        mBinding.appDrawerContactItem.setOnClickListener {
            replaceFragment(ContactDrawerFragment())
            mDrawer.closeDrawer(GravityCompat.START)
        }
        mBinding.appDrawerMoreItem.setOnClickListener {
            replaceFragment(MoreDrawerFragment())
            mDrawer.closeDrawer(GravityCompat.START)
        }
    }

    private fun navSearch() {
        mBinding.search.setOnClickListener {
            replaceActivity(SearchActivity())
        }
    }

    private fun initFunc() {
        AUTH = FirebaseAuth.getInstance()
        if (AUTH.currentUser != null) {
            setSupportActionBar(mToolbar)
            replaceFragment(Home())
        } else {
            replaceActivity(LoginActivity())
        }

    }

    private fun navStart() {
        replaceFragment(Home())
        mBinding.fragmentName.setText(R.string.home)
    }


    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        setSupportActionBar(mToolbar)
        mDrawer = mBinding.drawer
        mBinding.hamburgerIcon.setOnClickListener {
            mDrawer.openDrawer(GravityCompat.START)
        }
        initFirebase()
    }

    private fun bottomNavigation() {
        mBinding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(Home())
                    mBinding.fragmentName.setText(R.string.home)
                }
                R.id.notifications -> {
                    replaceFragment(Notifications())

                    mBinding.fragmentName.setText(R.string.notifications)
                }
                R.id.profile -> {
                    replaceFragment(Profile())
                    mBinding.fragmentName.setText(R.string.profile)
                }
                R.id.chats -> {
                    replaceFragment(Chats())
                    mBinding.fragmentName.setText(R.string.chats)
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.dataContainer, fragment)
        fragmentTransaction.commit()
    }
}