package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GMapFragment extends Fragment {
    private List<Place> places = new ArrayList<>();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GMapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GMapFragment newInstance(String param1, String param2) {
        GMapFragment fragment = new GMapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                mParam1 = getArguments().getString(ARG_PARAM1);
                mParam2 = getArguments().getString(ARG_PARAM2);
            }


            places.add(new Place("Google", new LatLng(-23.5868031, -46.6843406), "Av. Brg. Faria Lima, 3477 - 18º Andar - Itaim Bibi, São Paulo - SP", 4.8f));
            places.add(new Place("Parque", new LatLng(-23.5899619, -46.66747), "Av. República do Líbano, 1111 - Ibirapuera, São Paulo - SP", 4.9f));

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_g_map, container, false);

        Log.d("CREATION","aqui 2");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = getContext();
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    googleMap.setInfoWindowAdapter(new MarkerInfoAdapter(context));
                    addMarkers(googleMap,context);

                    googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                        @Override
                        public void onMapLoaded() {
                            LatLngBounds.Builder bounds = LatLngBounds.builder();

                            for (Place place : places) {
                                bounds.include(place.getLatLng());
                            }

                            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 100));
                        }
                    });
                }
            });
        }
        else{
            Log.d("CREATION","Deu merda");
        }

    }

    private void addMarkers(GoogleMap googleMap,Context context) {
        for (Place place : places) {
            MarkerOptions markerOptions = new MarkerOptions()
                    .title(place.getName())
                    .snippet(place.getAddress())
                    .position(place.getLatLng())
                    .icon(BitmapHelper.vectorToBitmap(
                            context,
                            R.drawable.ic_android_black_24dp,
                            ContextCompat.getColor(context, R.color.teal_200)
                    ));

            Marker marker = googleMap.addMarker(markerOptions);
            marker.setTag(place);
        }
    }
    }

class Place {
    private String name;
    private LatLng latLng;
    private String address;
    private float rating;

    public Place(String name, LatLng latLng, String address, float rating) {
        this.name = name;
        this.latLng = latLng;
        this.address = address;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public String getAddress() {
        return address;
    }

    public float getRating() {
        return rating;
    }
}




