package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * {@link EarthquakeAdapter} overrides getView method to allow for a more complex View to be returned by ArrayAdapter.
  */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    /**
     *
     * @param context of the app
     * @param earthquakes is the list of earthquakes, which will act as the data source for adapter
     */

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        //Initialize the ArrayAdapter's internal storage for the context and list
        //Second value is if populating single TextView.  Not used, so any value is applicable.  We use 0.
        super(context, 0, earthquakes);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //Check if existing view is being reused, otherwise inflate the view.
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,false);
        }

        //Get the {@link Earthquake} object located at this position in the list
        Earthquake currentEarthquake = getItem(position);

        //Find the magnitude TextView and apply magnitude text from Earthquake object
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude_tv);

        DecimalFormat formatter = new DecimalFormat("0.0");

        String magString = formatter.format(currentEarthquake.getmEarthquakeMagnitude());

        magnitudeTextView.setText(magString);

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        int magnitudeColor = getMagnitudeColor(currentEarthquake.getmEarthquakeMagnitude());

        magnitudeCircle.setColor(magnitudeColor);

        //Find the location String from current Earthquake, and split into proper views
        String fullLocation = currentEarthquake.getmEarthquakeLocation();

        TextView nearTextView = (TextView)listItemView.findViewById(R.id.near_tv);

        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location_tv);

        if(Character.isDigit(fullLocation.charAt(0))){
            String[] parts = fullLocation.split("(?<=f)");
            String subNear = parts[0];
            String subLocation = parts[1];
            nearTextView.setText(subNear);
            locationTextView.setText(subLocation);
        }else{
            String subNear = "Near the";
            nearTextView.setText(subNear);
            locationTextView.setText(currentEarthquake.getmEarthquakeLocation());
        }

        //Create new Date object from the time in milliseconds from Earthquake object
        Date dateObject = new Date(currentEarthquake.getmEarthquakeDate());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_tv);

        String formattedDate = formatDate(dateObject);

        dateTextView.setText(formattedDate);

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time_tv);

        String formattedTime = formatTime(dateObject);

        timeTextView.setText(formattedTime);

        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private int getMagnitudeColor(Double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
        }
    }

