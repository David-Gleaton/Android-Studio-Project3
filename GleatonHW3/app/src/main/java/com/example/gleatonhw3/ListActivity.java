package com.example.gleatonhw3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

/*
David Gleaton
3/18/21
This is the Main activity, adapted from the BandDatabase app in zybooks
 */


public class ListActivity extends AppCompatActivity implements ListFragment.OnLocationClickListener{

    private static final String KEY_LOCATION_ID = "locationID";
    private int mLocationId;



    //@pre:
    //@post: Returns the ListActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mLocationId = 0;


        //Setting up the fragment manager
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container_list);


        if (fragment == null) {
            fragment = new ListFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container_list, fragment).commit();
        }


        // Replace DetailsFragment if state saved when going from portrait to landscape
        if (savedInstanceState != null && savedInstanceState.getInt(KEY_LOCATION_ID) != 0
                && getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mLocationId = savedInstanceState.getInt(KEY_LOCATION_ID);
            Fragment locationFragment = DetailsFragment.newInstance(mLocationId);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_details, locationFragment)
                    .commit();
        }


    }

    //@pre:
    //@post: Saves the app instanceState for later use
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // Save state when something is selected
        if (mLocationId != -1) {
            savedInstanceState.putInt(KEY_LOCATION_ID, mLocationId);
        }
    }


    //@pre: List is populated
    //@post: Starts the DetailsActivity
    @Override
    public void OnLocationClick(int locationID){

        mLocationId = locationID;
        if(findViewById(R.id.fragment_container_details) == null){
            //Running in Portrait
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(DetailsActivity.EXTRA_LOCATION_ID, locationID);
            startActivity(intent);
        }else{
            //Running in Landscape
            Fragment locationFragment = DetailsFragment.newInstance(locationID);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_details, locationFragment)
                    .commit();
        }

    }

}