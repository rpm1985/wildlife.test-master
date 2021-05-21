package com.example.animalapp;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

public class MapFragment extends Fragment implements OnMapReadyCallback {


    private GoogleMap mMap;
    private static final LatLng FORESTFARM = new LatLng(51.516780,-3.243370);
    private static final LatLng HAMADRYADPARK = new LatLng(51.46067865, -3.1755209);
    private static final LatLng BUTEPARK = new LatLng(51.48849155, -3.18938255);
    private static final LatLng CATHAYSCEMETERY = new LatLng(51.50072866, -3.18105698);
    private static final LatLng LLANISHEN = new LatLng(51.52559354, -3.17539215);
    private static final LatLng ROATHPARK= new LatLng(51.50492273, -3.17530632);

    private Marker mForestFarm;
    private Marker mHamadryadPark;
    private Marker mButePark;
    private Marker mCathaysCemetery;
    private Marker mLlanishen;
    private Marker mRoathPark;

    private double moveCurrentLat;
    private double moveCurrentLong;




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        getActivity().setTitle(R.string.title_map);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            moveCurrentLat = bundle.getDouble("Latitude");
            moveCurrentLong = bundle.getDouble("Longitude");
        } else {
            moveCurrentLat = 51.481583;
            moveCurrentLong = -3.179090;
        }

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        SupportMapFragment fragment = new SupportMapFragment();
        transaction.add(R.id.map, fragment);
        transaction.commit();
        fragment.getMapAsync(this);


        return view;

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        double latitude = 51.481583;
        double longitude = -3.179090;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if(ContextCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            }
        }


        // Add a marker in Sydney and move the camera
        LatLng cardiff = new LatLng(latitude, longitude);
        mMap.getUiSettings().setZoomControlsEnabled(true);
//        mMap.setPadding(0, 0, 0, 120);
        mMap.addMarker(new MarkerOptions().position(cardiff).title("Marker in Cardiff").draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cardiff));
        LatLng coordinate = new LatLng(moveCurrentLat, moveCurrentLong);
        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 14);
        mMap.animateCamera(yourLocation);

//        Adding Markers to Green Locations
        mForestFarm = mMap.addMarker(new MarkerOptions()
                .position(FORESTFARM)
                .title("Forest Farm").snippet("Birds, dragonflies and fishes like the Kingfisher.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mButePark = mMap.addMarker(new MarkerOptions()
                .position(BUTEPARK)
                .title("Bute Park").snippet("Bats and squirrels ")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mCathaysCemetery = mMap.addMarker(new MarkerOptions()
                .position(CATHAYSCEMETERY)
                .title("Cathays Cemetery").snippet("Area with lots of wildlife.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mHamadryadPark = mMap.addMarker(new MarkerOptions()
                .position(HAMADRYADPARK)
                .title("Cardiff Bay Wetlands Reserve and Hamadryad Park ").snippet("Whitethroats, Reed Warblers and Sedge Warblers.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mLlanishen = mMap.addMarker(new MarkerOptions()
                .position(LLANISHEN)
                .title("Lisvane and Llanishen Reservoirs ").snippet("Grass Snakes,Buzzards and Jackdaws.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mRoathPark = mMap.addMarker(new MarkerOptions()
                .position(ROATHPARK)
                .title("Roath Park").snippet("Ducks and Geese .")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

    }


    }

