package com.example.gleatonhw3;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/*
David Gleaton
3/18/21
ListFragment is adapted from the BandDatabase app in zybooks
 */
public class ListFragment extends Fragment {


    //@pre:
    //@post: Is the interface for implementation in a different class
    public interface OnLocationClickListener{
        void OnLocationClick(int locationID);
    }

    private OnLocationClickListener mListener;

    public ListFragment() {
        // Required empty public constructor
    }


    //@pre:
    //@post: Returns a Fragment of the ListFragment type
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container_list);

        if (fragment == null) {
            fragment = new ListFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container_list, fragment)
                    .commit();
        }



    }

    //@pre: ListFragment is created
    //@post: Starts a RecyclerView type list within the ListFragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.location_recycler_view);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Send locations to recycler view
        LocationAdapter adapter = new LocationAdapter(LocationDatabase.getInstance(getContext()).getLocations());
        recyclerView.setAdapter(adapter);

        return view;
    }

    //@pre: onCreateView has been called
    //@post: Returns the holder objects for the list items in the Recycler view
    private class LocationHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Location mLocation;

        private TextView mNameTextView;
        private ImageView mImageView;

        public LocationHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_location, parent, false));
            itemView.setOnClickListener(this);
            mNameTextView = itemView.findViewById(R.id.locationName);

            mImageView = itemView.findViewById(R.id.WeatherListIcon);

        }

        public void bind(Location location) {
            mLocation = location;
            mNameTextView.setText(mLocation.getName());
            Resources res = getResources();
            int resID;
            resID = res.getIdentifier("_"+mLocation.getmWeatherIcon(), "drawable", "com.example.gleatonhw3");
            mImageView.setImageResource(resID);
        }

        @Override
        public void onClick(View view) {
            // Tell ListActivity what location was clicked
            mListener.OnLocationClick(mLocation.getId());
        }
    }

    //@pre: OnCreate has been called
    //@post: Returns an adapter type for use in the RecyclerView
    private class LocationAdapter extends RecyclerView.Adapter<LocationHolder> {

        private List<Location> mLocations;

        public LocationAdapter(List<Location> locations) {
            mLocations = locations;
        }

        @Override
        public LocationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new LocationHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(LocationHolder holder, int position) {
            Location location = mLocations.get(position);
            holder.bind(location);
        }

        @Override
        public int getItemCount() {
            return mLocations.size();
        }
    }



    //@pre:
    //@post: Attaches the fragment to the calling activity
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof OnLocationClickListener){
            mListener = (OnLocationClickListener) context;
        }else{
            throw new RuntimeException(context.toString() + " needs to implement OnLocationClickListener");
        }
    }

    //@pre:
    //@post: Detaches the fragment to the calling activity
    @Override
    public void onDetach(){
        super.onDetach();
        mListener = null;
    }



}