package com.example.gleatonhw3;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/*
David Gleaton
3/18/21
This Database is a combo of the Bandatabase app and the Study App Volley section.
This Database populates a list of Locations with their information for later use.
 */

public class LocationDatabase {
    private static LocationDatabase sLocationDatabase;
    private List<Location> mLocations;
    private int locationNum;


    //@pre: context
    //@post: Returns the database, or creates and returns a database if none have been created
    public static LocationDatabase getInstance(Context context) {
        if (sLocationDatabase == null) {
            sLocationDatabase = new LocationDatabase(context);
        }
        return sLocationDatabase;
    }


    //@pre: context
    //@post: Returns the database with full information
    private LocationDatabase(Context context) {
        mLocations = new ArrayList<>();
        Resources res = context.getResources();
        String[] locations = res.getStringArray(R.array.WeatherLocations);
        String[] descriptions = res.getStringArray(R.array.Location_Descriptions);
        //Building of the API Calls
        String[] api_lat_lon = res.getStringArray(R.array.API_Lat_Lon);
        String[] api_calls = new String[20];
        String api_end = res.getString(R.string.API_END);
        String api_start = res.getString(R.string.WEB_URL);


        //Iterator for the Weather Locations
        locationNum = 0;
        //RequestQueue for Volley API
        RequestQueue queue = Volley.newRequestQueue(context);
        //Creation of the locations
        for(int i = 0; i < locations.length; i++){
            mLocations.add(new Location(i + 1, locations[i], descriptions[i]));
        }
        //Iterate through the API_CALLS for information
        for (int i = 0; i < api_lat_lon.length; i++){
            //Build the API-Link
            api_calls[i] = api_start+api_lat_lon[i] + api_end;
            JsonObjectRequest requestObj = new JsonObjectRequest
                    (Request.Method.GET, api_calls[i], null, new Response.Listener<JSONObject>() {
                        //Set the information into the models
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                //Separate the current from the response
                                JSONObject current = response.getJSONObject("current");
                                //Set the basic information into their respective models
                                mLocations.get(locationNum).setmDT(current.getString("dt"));
                                mLocations.get(locationNum).setmSunrise(current.getString("sunrise"));
                                mLocations.get(locationNum).setmSunset(current.getString("sunset"));
                                mLocations.get(locationNum).setTemp(current.getString("temp"));
                                mLocations.get(locationNum).setmFeels_Like(current.getString("feels_like"));
                                mLocations.get(locationNum).setmPressure(current.getString("pressure"));
                                mLocations.get(locationNum).setmHumidity(current.getString("humidity"));
                                mLocations.get(locationNum).setmDew_Point(current.getString("dew_point"));
                                mLocations.get(locationNum).setmUVI(current.getString("uvi"));
                                mLocations.get(locationNum).setmClouds(current.getString("clouds"));
                                mLocations.get(locationNum).setmVisibility(current.getString("visibility"));
                                mLocations.get(locationNum).setmWind_Speed(current.getString("wind_speed"));
                                mLocations.get(locationNum).setmWind_Deg(current.getString("wind_deg"));

                                //Separate out weather and Set
                                JSONArray weatherArray = current.getJSONArray("weather");
                                JSONObject weather = weatherArray.getJSONObject(0);
                                mLocations.get(locationNum).setmWeatherDescription(weather.getString("description"));
                                mLocations.get(locationNum).setmWeatherIcon(weather.getString("icon"));

                                locationNum++;

                            }catch (Exception e) {
                                Log.e("API-ERROR", "One or more fields not found in the JSON data");
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("API-ERROR", "Error: " + error.toString());
                        }
                    });
            queue.add(requestObj);
        }
        /*Sleep section to cover async API calls. This is to mainly have the weather icons appear on the Listactivity, but if
            this exceeds 1.5 seconds, a simple rotate of the phone will set it
        */
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



    //@pre: mLocations has been set
    //@post: Returns mLocations
    public List<Location> getLocations() {
        return mLocations;
    }

    //@pre: mLocations has been set
    //@post: returns the location at the passed in index
    public Location getLocation(int locationID) {
        for (Location location : mLocations) {
            if (location.getId() == locationID) {
                return location;
            }
        }
        return null;
    }



}
