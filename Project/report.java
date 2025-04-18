package com.example.waterwise;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class report extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 101;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;

    private ImageView imageView;
    private TextView txtLocation;
    private EditText edtDescription;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btnCaptureImage = findViewById(R.id.btnCaptureImage);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btnGetLocation = findViewById(R.id.btnGetLocation);
        imageView = findViewById(R.id.imageView);
        txtLocation = findViewById(R.id.txtLocation);
        edtDescription = findViewById(R.id.edtDescription);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Capture image
        btnCaptureImage.setOnClickListener(v -> openCamera());

        // Get location
        btnGetLocation.setOnClickListener(v -> fetchLocation());
    }

    // Open the camera
    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
        } else {
            Toast.makeText(this, "Camera not supported", Toast.LENGTH_SHORT).show();
        }
    }

    // Handle camera result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }

    // Fetch location
    private void fetchLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Request location permissions
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Get last known location
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {
                if (location != null) {
                    String locationText = "Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude();
                    txtLocation.setText(locationText);
                } else {
                    Toast.makeText(this, "Unable to fetch location", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    // Handle permissions result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLocation();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}