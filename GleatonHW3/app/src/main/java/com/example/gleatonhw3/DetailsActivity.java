package com.example.gleatonhw3;


import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
/*
David Gleaton
3/18/21
This DetailsActivity class is adapted from the zybooks BandDatabase app, and is fitted to work with weather locations
 */

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_LOCATION_ID = "locationID";

    // Terminate if two panes are displaying since ListActivity should be displaying both panes


    //@pre: ListFragment is completed
    //@post: Creates a DetailsActivity Fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }


        setContentView(R.layout.activity_details);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container_details);

        if (fragment == null) {
            int locationID = getIntent().getIntExtra(EXTRA_LOCATION_ID, 1);
            fragment = DetailsFragment.newInstance(locationID);
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container_details, fragment)
                    .commit();

        }

    }
}