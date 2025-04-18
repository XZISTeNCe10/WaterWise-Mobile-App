package com.example.waterwise;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class waterfragment extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waterfragment);

        // Initialize the map fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map3);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        } else {
            Log.e("WaterFragment", "Map Fragment is null. Check layout ID.");
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        if (googleMap == null) {
            Log.e("WaterFragment", "Google Map is null.");
            return;
        }

        this.gMap = googleMap;

        // Clear the map before adding markers
        gMap.clear();

        // List of locations to display on the map
        List<Location> locations = new ArrayList<>();
        locations.add(new Location(19.033322, 73.065430, "DrinkingZone Water Supplier"));
        locations.add(new Location(19.033662, 73.061140, "Bisleri Water Supplier"));
        locations.add(new Location(19.034445, 73.078092, "Vasan Enterprises"));
        locations.add(new Location(19.035287, 73.058977, "EverydaySure"));
       

        // Debug log to confirm locations are being added
        Log.d("WaterFragment", "Adding markers to the map");

        // Add markers for each location
        for (Location location : locations) {
            LatLng latLng = new LatLng(location.latitude, location.longitude);
            gMap.addMarker(new MarkerOptions().position(latLng).title(location.title));
            Log.d("WaterFragment", "Added marker at: " + latLng + " with title: " + location.title);
        }
    }

    // Helper class for storing location data
    static class Location {
        double latitude;
        double longitude;
        String title;

        Location(double latitude, double longitude, String title) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.title = title;
        }
    }
}
