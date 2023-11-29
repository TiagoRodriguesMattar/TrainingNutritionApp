package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GMapFragment extends Fragment {

    private List<Place.Field> placeFields = Arrays.asList(Place.Field.NAME, Place.Field.ADDRESS, Place.Field.TYPES,Place.Field.LAT_LNG,Place.Field.RATING);
    private AutocompleteSessionToken token;
    private static final int REQUEST_CODE = 100;
    private FusedLocationProviderClient fusedLocationProviderClient;
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
        Context context = getContext();



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_g_map, container, false);

        Log.d("CREATION", "aqui 2");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext());

        if (hasLocationPermission()) {
            fetchLastLocation();
        } else {
            requestLocationPermission();
        }

    }

    private void mapa(List<Lugares> places) {
        Context context = getContext();
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment);
        if (mapFragment != null && !places.isEmpty()) {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    googleMap.setInfoWindowAdapter(new MarkerInfoAdapter(context));
                    addMarkers(googleMap, context,places);

                    googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                        @Override
                        public void onMapLoaded() {
                            LatLngBounds.Builder bounds = LatLngBounds.builder();
                            if(places.isEmpty()) {
                                Log.i("Places","List places vazaia");
                            }
                            for (Lugares place : places) {
                                Log.d("CREATION","Place: " + place.getName());
                                bounds.include(place.getLatLng());
                            }

                            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 100));
                        }
                    });
                }
            });
        } else {
            Log.d("CREATION", "Deu merda");
        }
    }

    private void addMarkers(GoogleMap googleMap, Context context, List<Lugares> places) {
        for (Lugares lugar : places) {
            MarkerOptions markerOptions = new MarkerOptions()
                    .title(lugar.getName())
                    .snippet(lugar.getAddress())
                    .position(lugar.getLatLng())
                    .icon(BitmapHelper.vectorToBitmap(
                            context,
                            R.drawable.ic_android_black_24dp,
                            ContextCompat.getColor(context, R.color.teal_200)
                    ));

            Marker marker = googleMap.addMarker(markerOptions);
            marker.setTag(lugar);
        }
    }


    private boolean hasLocationPermission() {
        Log.d("CREATION", "hasLocation func");
        return ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermission() {
        Log.d("CREATION", "Nao tinha permissao");
        registerForActivityResult(new ActivityResultContracts.RequestPermission(), result -> {
            if (result) {
                Log.d("CREATION", "Aceitou tracking");
                // Permissão concedida, busque a localização
                fetchLastLocation();
            } else {
                Log.d("CREATION", "Nao aceitou tracking");
                // Permissão negada, lide com isso de acordo com o fluxo do seu aplicativo
            }
        }).launch(Manifest.permission.ACCESS_FINE_LOCATION);
    }

    private void fetchLastLocation() {
        List<Lugares> places = new ArrayList<>();
        //LatLng location = new LatLng(latitude, longitude);
        Log.d("CREATION", "Tinha permissao");
        Context context = getContext();
        Places.initialize(context, "AIzaSyCOWtv5Yv1lldby8jGMTbldY-rz2lerg5g");
        token = AutocompleteSessionToken.newInstance();

        PlacesClient placesClient = Places.createClient(context);
        FindCurrentPlaceRequest request = FindCurrentPlaceRequest.builder(placeFields)
                .build();



        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("Places","No permission");
        }
        Task<FindCurrentPlaceResponse> placeResponse = placesClient.findCurrentPlace(request);


        placeResponse.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FindCurrentPlaceResponse response = task.getResult();
                List<PlaceLikelihood> placesLikelihoodList = response.getPlaceLikelihoods();
                com.google.android.libraries.places.api.model.Place location = placesLikelihoodList.get(0).getPlace();
                places.add(new Lugares(
                        location.getName(),
                        location.getLatLng(),
                        location.getAddress(),
                        4.8f
                ));

                for (PlaceLikelihood placeLikelihood : placesLikelihoodList) {
                    //places.add(new Lugares("Google",new LatLng(-22.9626987,-47.0240473),"Luís Caetano, 193 - Nações, Valinhos - SP, 13271-785",4.8f));
                    com.google.android.libraries.places.api.model.Place place = placeLikelihood.getPlace();
                       for(int i=0;i<place.getTypes().size();i++) {
                           if (place.getTypes().get(i).toString() == "CAR_WASH" || place.getTypes().get(i).toString() == "GYM") {
                               //places.add(new Lugares("Google", new LatLng(-22.9626987, -47.0240473), "Luís Caetano, 193 - Nações, Valinhos - SP, 13271-785", 4.8f));
                               try {
                                   places.add(new Lugares(
                                           place.getName(),
                                           place.getLatLng(),
                                           place.getAddress(),
                                           4.8f
                                   ));
                               } catch (Exception e) {
                                   Log.e("Places", "Erro: " + e);
                               }
                           }

                       }

                    // Aqui você pode usar as informações do lugar, como nome, localização, etc.
                }

                mapa(places);
            } else {
                Log.e("Places", "Failed to get places: " + task.getException());
            }

        }); //

        /*
        * try {

            FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                    .setCountry("BR")
                    .setSessionToken(token)
                    .setTypeFilter(TypeFilter.ESTABLISHMENT)
                    .build();

            placesClient.findAutocompletePredictions(request)
                    .addOnCompleteListener(new OnCompleteListener<FindAutocompletePredictionsResponse>() {
                        @Override
                        public void onComplete(@NonNull Task<FindAutocompletePredictionsResponse> task) {
                            if (task.isSuccessful()) {
                                FindAutocompletePredictionsResponse result = task.getResult();
                                if (result != null) {
                                    List<AutocompletePrediction> predictions = result.getAutocompletePredictions();
                                    for (AutocompletePrediction p : predictions) {
                                        Log.d("Places", "Place " + p.getPlaceTypes());
                                    }

                                } else {
                                    Log.d("Places", "result == null");
                                }
                            } else {
                                Log.e("Places", "Failed to get places: " + task.getException());
                            }
                        }

                    });
        }
        catch (Exception e){
            Log.d("Places","Erro: " + e);
        }*/


    }


}

class Lugares {
    private String name;
    private LatLng latLng;
    private String address;
    private double rating;

    public Lugares(String name, LatLng latLng, String address, double rating) {
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

    public double getRating() {
        return rating;
    }
}




