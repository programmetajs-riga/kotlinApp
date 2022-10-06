package com.example.kotlintask

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kotlintask.databinding.ActivityMainBinding
import com.example.kotlintask.ui.home.HomeFragment
import com.example.kotlintask.ui.place.PlaceFragment
import com.example.kotlintask.ui.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var navView: BottomNavigationView

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        LoginActivity.loginActivity!!.finish()

         navView= binding.navView

        navView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home -> navigationFragment(HomeFragment())
                R.id.navigation_place -> navigationFragment(PlaceFragment())
                R.id.navigation_settings -> navigationFragment(SettingsFragment())

                else ->{

                }
            }
            true
        }

    }

    private fun navigationFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        if(fragment.isAdded){
            fragmentTransaction
                .setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out)
                .show(fragment)
                .commit()
        }else{
            fragmentTransaction
                .setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out)
                .add(R.id.nav_host_fragment_activity_main , fragment)
                .show(fragment)
                .commit()
        }
    }

}