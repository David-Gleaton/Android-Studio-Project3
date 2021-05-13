package com.example.gleatonhw3;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.widget.ResourceManagerInternal;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;

/*
David Gleaton
3/18/21
This Details Fragment is heavily adapted from the BandDatabase app
 */

public class DetailsFragment extends Fragment {

    private Location mLocation;

    //@pre:
    //@post: Returns a DetailsFragment with the locationID
    public static DetailsFragment newInstance(int locationID) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt("locationID", locationID);
        fragment.setArguments(args);
        return fragment;
    }



    //@pre:
    //@post: Sets the mLocation object to the LocationDatabase structure
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int locationID = 1;
        if (getArguments() != null) {
            locationID = getArguments().getInt("locationID");
        }

        mLocation = LocationDatabase.getInstance(getContext()).getLocation(locationID);
    }


    //@pre: OnCreate has been run
    //@post: Inflates the fragment details layout file and sets all the data for the DetailsFragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        //The setting of Location Name
        TextView nameTextView = view.findViewById(R.id.LocationName);
        nameTextView.setText(mLocation.getName());
        TextView descriptionTextView = view.findViewById(R.id.LocationDesc);
        descriptionTextView.setText(mLocation.getDescription());

        //The setting of Date text
        TextView dateTextView = view.findViewById(R.id.Date);
        Date Date = new java.util.Date(Long.parseLong(mLocation.getmDT())*1000L);
        int hour = Date.getHours();
        int minutes = Date.getMinutes();
        String minutesEdited;
        if(minutes < 10){
            minutesEdited =  "0"+minutes;
        }else{
            minutesEdited = minutes + "";
        }
        String DateFinal;
        if(hour > 12){
            hour -= 12;
            DateFinal = getString(R.string.Date_Top)+ hour + ":"+minutesEdited+ " p.m.";
        }else{
            DateFinal = getString(R.string.Date_Top)+ hour + ":"+minutesEdited+ " a.m.";
        }
        dateTextView.setText(DateFinal);

        //The Setting of sunrise text
        TextView sunriseTextView = view.findViewById(R.id.Sunrise);
        Date SunriseDate = new java.util.Date(Long.parseLong(mLocation.getmSunrise())*1000L);
        hour = SunriseDate.getHours();
        minutes = SunriseDate.getMinutes();
        minutesEdited = "";
        if(minutes < 10){
            minutesEdited =  "0"+minutes;
        }else{
            minutesEdited = minutes + "";
        }
        String SunriseDateFinal;
        if(hour > 12){
            hour -= 12;
            SunriseDateFinal = hour + ":"+minutesEdited+" p.m.";
        }else{
            SunriseDateFinal = hour + ":"+minutesEdited+" a.m.";
        }

        sunriseTextView.setText(SunriseDateFinal);


        //Setting Sunset
        TextView sunsetTextView = view.findViewById(R.id.Sunset);
        Date SunsetDate = new java.util.Date(Long.parseLong(mLocation.getmSunset())*1000L);
        hour = SunsetDate.getHours();
        minutes = SunsetDate.getMinutes();
        minutesEdited = "";
        if(minutes < 10){
            minutesEdited =  "0"+minutes;
        }else{
            minutesEdited = minutes + "";
        }
        String SunsetDateFinal;
        if(hour > 12){
            hour -= 12;
            SunsetDateFinal = hour + ":"+minutesEdited+" p.m.";
        }else{
            SunsetDateFinal = hour + ":"+minutesEdited+" a.m.";
        }

        sunsetTextView.setText(SunsetDateFinal);


        //The setting of temp text
        TextView detailsTextView = view.findViewById(R.id.LocationTemp);
        String Temperature = mLocation.getTemp() + " F";
        detailsTextView.setText(Temperature);

        //The setting of FeelsLike
        TextView FeelsLikeTextView = view.findViewById(R.id.FeelsLike);
        String FeelsLike = getString(R.string.Feels_Like) + mLocation.getmFeels_Like() + " F";
        FeelsLikeTextView.setText(FeelsLike);

        //The setting of Pressure
        TextView PressureTextView = view.findViewById(R.id.Pressure);
        String Pressure = getString(R.string.hPa) + mLocation.getmPressure()+ " hPa";
        PressureTextView.setText(Pressure);

        //The Setting of Humidity
        TextView HumidityTextView = view.findViewById(R.id.Humidity);
        String Humidity = getString(R.string.Humidity_Title)+mLocation.getmHumidity() + " %";
        HumidityTextView.setText(Humidity);

        //The Setting of Dew_point
        TextView Dew_pointTextView = view.findViewById(R.id.Dew_point);
        String Dew_Point = getString(R.string.Dew_Point) + mLocation.getmDew_Point() + " F";
        Dew_pointTextView.setText(Dew_Point);

        //The Setting of UVI
        TextView UVITextView = view.findViewById(R.id.UVI);
        String UVIIndex = getString(R.string.UVI_Index)+mLocation.getmUVI();
        UVITextView.setText(UVIIndex);

        //The setting of Cloud %
        TextView CloudTextView = view.findViewById(R.id.Clouds);
        String Clouds = getString(R.string.Cloud_Cover)+mLocation.getmClouds() + " %";
        CloudTextView.setText(Clouds);

        //The setting of Visibility
        TextView VisibilityTextView = view.findViewById(R.id.Visibility);
        String Visilibity = getString(R.string.Visibility_title) + mLocation.getmVisibility() +" feet";
        VisibilityTextView.setText(Visilibity);

        //The setting of Wind Speed
        TextView WindSpeedTextView = view.findViewById(R.id.WindSpeed);
        String Wind_Speed = getString(R.string.WindSpeed) + mLocation.getmWind_Speed() + " mph";
        WindSpeedTextView.setText(Wind_Speed);

        //The Setting of Wind Deg
        TextView WindDegTextView = view.findViewById(R.id.WindDeg);
        int WindDeg = Integer.parseInt(mLocation.getmWind_Deg());
        String WindDirection;
        if(WindDeg < 10 && WindDeg > 350){
            WindDirection = "N";
        }else if(WindDeg >10 && WindDeg < 80){
            WindDirection = "NE";
        }else if(WindDeg >80 && WindDeg<100){
            WindDirection = "E";
        }else if(WindDeg > 100 && WindDeg<170){
            WindDirection = "SE";
        }else if(WindDeg >170 && WindDeg<190){
            WindDirection = "S";
        }else if(WindDeg > 190 && WindDeg<260){
            WindDirection = "SW";
        }else if(WindDeg >260 && WindDeg<280){
            WindDirection = "W";
        }else{
            WindDirection = "NW";
        }

        String Wind_Deg = getString(R.string.WindDeg) + WindDirection;
        WindDegTextView.setText(Wind_Deg);

        //Setting the WeatherDescription
        TextView WeatherDescriptionTextView = view.findViewById(R.id.WeatherDescription);
        String WeatherDescriptionRaw = mLocation.getmWeatherDescription();
        String[] StringArray = WeatherDescriptionRaw.split(" ");
        StringBuilder buildme = new StringBuilder();
        for(String s : StringArray){
            String cap = s.substring(0,1).toUpperCase() + s.substring(1);
            buildme.append(cap +" ");
        }

        WeatherDescriptionTextView.setText(buildme.toString());

        //WeatherIcon
        ImageView WeatherIconImageView = view.findViewById(R.id.WeatherIcon);
        Resources res = getResources();
        int resID = res.getIdentifier("_"+mLocation.getmWeatherIcon(), "drawable", "com.example.gleatonhw3");
        WeatherIconImageView.setImageResource(resID);


        return view;
    }
}