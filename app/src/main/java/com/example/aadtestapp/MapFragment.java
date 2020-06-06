package com.example.aadtestapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    boolean mapAvailable;
    private static final String TAG = "Map";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9001;



    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView Map");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate Map");
        mapAvailable = isServicesAvailable();
        if (!mapAvailable) {
            Toast.makeText(getContext(), "Google Maps Unavailable", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onCreate Map is not available");
        }


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Fragment fragment = getChildFragmentManager().findFragmentById(R.id.map);
//        if (fragment == null){
//            Log.d(TAG,"FRAGMENT NULL!!!!");
//
//        }
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady");
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public boolean isServicesAvailable() {
        Log.d(TAG, "isServicesAvailable: checking google services version");
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int availability = googleAPI.isGooglePlayServicesAvailable(getContext());
        if (availability != ConnectionResult.SUCCESS) {
            if (googleAPI.isUserResolvableError(availability)) {
                googleAPI.getErrorDialog(getActivity(), availability, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }
            return false;
        }
        return true;
    }

}

