package com.example.gleatonhw3;

/*
David Gleaton
3/18/21
This is adapted initially from the BandDatabase App in zybooks.
This is the model for Weather Location information
 */



public class Location {

    private int mId;
    private String mName;
    private String mDescription;
    private String mDT;
    private String mSunrise;
    private String mSunset;
    private String mTemp;
    private String mFeels_Like;
    private String mPressure;
    private String mHumidity;
    private String mDew_Point;
    private String mUVI;
    private String mClouds;
    private String mVisibility;
    private String mWind_Speed;
    private String mWind_Deg;
    private String mWeatherDescription;
    private String mWeatherIcon;

    //Constructor that sets certain items to N/A if the API returns nothing
    public Location(int id, String name, String description) {
        mId = id;
        mName = name;
        mDescription = description;
        mDT = "N/A";
        mSunrise = "N/A";
        mSunset = "N/A";
        mTemp = "N/A";
        mFeels_Like = "N/A";
        mPressure = "N/A";
        mHumidity = "N/A";
        mDew_Point = "N/A";
        mUVI = "N/A";
        mClouds = "N/A";
        mVisibility = "N/A";
        mWind_Speed = "N/A";
        mWind_Deg = "N/A";


    }




    //Long list of Getters and Setters for the variables in the model
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setTemp(String temp){ mTemp = temp; }

    public String getTemp() { return mTemp; }

    public void setmFeels_Like(String feels_like){ mFeels_Like = feels_like; }

    public String getmFeels_Like(){ return mFeels_Like; }

    public void setmPressure(String pressure){ mPressure = pressure; }

    public String getmPressure(){ return mPressure; }

    public void setmHumidity(String humidity){ mHumidity = humidity;}

    public String getmHumidity(){ return mHumidity; }

    public void setmDew_Point(String dew_point){ mDew_Point = dew_point;}

    public String getmDew_Point(){ return mDew_Point;}

    public void setmUVI(String uvi){ mUVI = uvi; }

    public String getmUVI(){ return mUVI; }

    public void setmClouds(String clouds){ mClouds = clouds;}

    public String getmClouds(){ return mClouds;}

    public void setmVisibility(String visibility){ mVisibility = visibility;}

    public String getmVisibility(){ return mVisibility; }

    public void setmWind_Speed(String wind_speed){ mWind_Speed = wind_speed; }

    public String getmWind_Speed(){ return mWind_Speed; }

    public void setmWind_Deg(String wind_deg){ mWind_Deg = wind_deg; }

    public String getmWind_Deg(){ return mWind_Deg; }

    public void setmDT(String dt){ mDT = dt; }

    public String getmDT(){ return mDT; }

    public void setmSunrise(String sunrise){ mSunrise = sunrise; }

    public String getmSunrise(){ return mSunrise; }

    public void setmSunset(String sunset){ mSunset = sunset; }

    public String getmSunset(){ return mSunset; }

    public void setmWeatherDescription(String weatherDescription){ mWeatherDescription = weatherDescription; }

    public String getmWeatherDescription(){ return mWeatherDescription; }

    public void setmWeatherIcon(String weatherIcon){ mWeatherIcon = weatherIcon; }

    public String getmWeatherIcon(){ return mWeatherIcon; }


}
