package com.organicpy.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.organicpy.myapplication.databinding.ActivityMainBinding;
import com.organicpy.myapplication.util.DetectConnectivity;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG;

/**
 * Main Activity, implements NetworkStatusCallback interface
 * @author Mohd Hussain
 * @version 1.0
 * @since 12-01-2021
 */
public class MainActivity extends AppCompatActivity implements DetectConnectivity.NetworkStatusCallBack {
    BroadcastReceiver broadcastReceiver;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // add fragment to FragmentContainerView
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainContainer, new MainFragment(), "Home")
                    .commit();
        }

        // Broadcast Receiver
        broadcastReceiver = new DetectConnectivity(this);

        // issue to be fixed / or functionality
        bottomNavigationView = findViewById(R.id.bottomNavigation);

        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFrag = null;
            if (item.getItemId() == R.id.home_frag) selectedFrag = new MainFragment();
            if (item.getItemId() == R.id.offer_frag) selectedFrag = new OfferFragment();
            if (item.getItemId() == R.id.rx_frag) selectedFrag = new RxFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainContainer, selectedFrag)
                    .addToBackStack("tag")
                    .commit();
            return true;
        });


    }

    @Override
    public void onBackPressed() {
        if (bottomNavigationView.getSelectedItemId() == R.id.home_frag) {
//            super.onBackPressed();
            finishAffinity();
        } else {
            bottomNavigationView.setSelectedItemId(R.id.home_frag);
        }
    }

    /**
     * Register broadcast and send filter when OnResume method called
     */
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    /**
     * Unregister broadcast whenever the activity onPause
     */
    @Override
    protected void onPause() {
        this.unregisterReceiver(broadcastReceiver);
        super.onPause();
    }

    /**
     * abstract function of NetworkStatusCallback, whenever network disconnected,
     * snackbar notified the user about the happening
     */
    @Override
    public void onStateChange() {
        Snackbar.make(findViewById(android.R.id.content), "Network Disconnected", LENGTH_LONG).show();
    }
}