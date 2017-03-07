package com.example.android.focusednews;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jason Jia on 3/5/2017.
 */

public class NewsDataBaseAdapter extends ArrayAdapter<NewsDataBase> {

    private static final String DATE_TIME_SEPARATOR = "T";

    public NewsDataBaseAdapter(Activity context, ArrayList<NewsDataBase> words) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
    }

    static class ViewHolder {
        private TextView countryTextView;
        private TextView sectionnameTextView;
        private TextView newstitleTextView;
        private TextView dateTextView;
        private TextView timeTextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //instance of ViewHolder
        ViewHolder viewHolder;

        // Check if the existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_layout, parent, false); //link the layout with the viewholder
            viewHolder = new ViewHolder();
            viewHolder.countryTextView = (TextView) convertView.findViewById(R.id.item_country);
            viewHolder.sectionnameTextView = (TextView) convertView.findViewById(R.id.item_sectionid);
            viewHolder.newstitleTextView = (TextView) convertView.findViewById(R.id.item_title);
            viewHolder.dateTextView = (TextView) convertView.findViewById(R.id.item_date);
            viewHolder.timeTextView = (TextView) convertView.findViewById(R.id.item_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final NewsDataBase currentItem = getItem(position);

        viewHolder.countryTextView.setText(currentItem.getCountryName());
        viewHolder.sectionnameTextView.setText(currentItem.getSectionName());
        viewHolder.newstitleTextView.setText(currentItem.getNewsTitle());

        //splitting date and time
        String originalX = currentItem.getDateTime();
        String[] parts = originalX.split(DATE_TIME_SEPARATOR);
        viewHolder.dateTextView.setText(parts[0]);
        viewHolder.timeTextView.setText(parts[1]);

        // Set the proper background color on the country circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable countryCircle = (GradientDrawable) viewHolder.countryTextView.getBackground();

        // Get the appropriate background color based on the country
        int countryColor = getCountryColor(currentItem.getCountryName());

        // Set the color on the magnitude circle
        countryCircle.setColor(countryColor);

        return convertView;
    }

    private int getCountryColor(String currentCountry) {
        int countryColorResourceId;
        switch (currentCountry) {
            case "USA":
                countryColorResourceId = R.color.country_circle_US;
                break;
            case "UK":
                countryColorResourceId = R.color.country_circle_UK;
                break;
            case "CHN":
                countryColorResourceId = R.color.country_circle_CN;
                break;
            case "CAN":
                countryColorResourceId = R.color.country_circle_CA;
                break;
            default:
                countryColorResourceId = R.color.country_circle_US;
        }
        return ContextCompat.getColor(getContext(), countryColorResourceId);
    }
}
