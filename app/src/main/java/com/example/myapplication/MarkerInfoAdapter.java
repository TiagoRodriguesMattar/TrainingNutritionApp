package com.example.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class MarkerInfoAdapter implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public MarkerInfoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        Object tag = marker.getTag();
        if (tag instanceof Lugares) {
            Lugares place = (Lugares) tag;

            View view = LayoutInflater.from(context).inflate(R.layout.custom_marker_info, null);

            TextView txtTitle = view.findViewById(R.id.txt_title);
            TextView txtAddress = view.findViewById(R.id.txt_address);
            TextView txtRating = view.findViewById(R.id.txt_rating);

            txtTitle.setText(place.getName());
            txtAddress.setText(place.getAddress());
            txtRating.setText(context.getString(R.string.rating, place.getRating()));

            return view;
        }
        return null;
    }
}