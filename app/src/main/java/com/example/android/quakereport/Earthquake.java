package com.example.android.quakereport;

import java.net.URL;
import java.util.Date;

public class Earthquake {

    /**Location of the Earthquake*/
    private String mEarthquakeLocation;
    /**Magnitude of the Earthquake*/
    private Double mEarthquakeMagnitude;
    /**Date of the Earthquake*/
    private long mEarthquakeDate;
    /**URL of USGS page for Earthquake info*/
    private String mEarthquakeUrl;


    /**
     *
     * @param location is the city location of the Earthquake
     * @param magnitude is the magnitude (size) of the Earthquake
     * @param date is the date the earthquake occurred
     */

    public Earthquake(String location, Double magnitude, long date, String url){
        this.mEarthquakeDate = date;
        this.mEarthquakeLocation = location;
        this.mEarthquakeMagnitude = magnitude;
        this.mEarthquakeUrl = url;
    }

    /**
     * @return the location (city) of the earthquake
     */
    public String getmEarthquakeLocation() {
        return mEarthquakeLocation;
    }

    /**
     * @return the magnitude of the earthquake
     */
    public Double getmEarthquakeMagnitude() {
        return mEarthquakeMagnitude;
    }

    /**
     * @return the date the earthquake occurred
     */
    public long getmEarthquakeDate() {
        return mEarthquakeDate;
    }

    /**
     * @return the URL of USGS page on Earthquake in String form
     */
    public String getmEarthquakeUrl() {
        return mEarthquakeUrl;
    }
}
