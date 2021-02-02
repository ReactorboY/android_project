package com.organicpy.myapplicationkt

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.organicpy.myapplicationkt.util.ConnectionDetection

class MainActivity : AppCompatActivity() {

    // firebase Analytics initialization
    private lateinit var firebaseAnalytics: FirebaseAnalytics


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // firebase Analytics
        firebaseAnalytics = Firebase.analytics

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFragmentContainer, HomeFragment(), null)
            .commit()

        val connectionDetection = ConnectionDetection(this)
        connectionDetection.observe(this, { isConnected ->
            isConnected?.let {
                Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()
            }
        })

        val bottomNavigation : BottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            var selectedFrag: Fragment? = null
            when (item.itemId) {
                R.id.frag1 -> selectedFrag = HomeFragment()
                R.id.frag2 -> selectedFrag = OfferFragment()
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainFragmentContainer, selectedFrag!!, null)
                .commit()
            true
        }
    }
}