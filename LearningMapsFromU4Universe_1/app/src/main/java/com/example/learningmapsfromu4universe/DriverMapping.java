package com.example.learningmapsfromu4universe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DriverMapping extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, GoogleMap.OnMapLongClickListener {



    private GoogleMap mMap;
    public static final int PERMISSION_REQUEST_CODE = 9001;

    Button btn_show_directions, btn_clear_directions;

    private GoogleApiClient client;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentLocationMarker, usercurrentLocationMarker;

    Polyline polyline = null;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    List<LatLng> latLngList = new ArrayList<>();
    List<Marker> markerList = new ArrayList<>();


    ArrayList<LatLng> markerPoints;

    // private MarkerOptions place1, place2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_mapping);

        markerPoints = new ArrayList<LatLng>();




        btn_show_directions = findViewById(R.id.showDirection);
        btn_clear_directions = findViewById(R.id.clearDirection);

        btn_show_directions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();

        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }







    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
            }
            return false;
        } else
            return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted !
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        if (client == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                } else { // permision denied
                    Toast.makeText(this, "Permission Denied !", Toast.LENGTH_SHORT).show();
                }
                return;
        }

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


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {

            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User Current Location");


        databaseReference.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                Double Latitude = dataSnapshot.child("latitude").getValue(Double.class);
                Double Longitude = dataSnapshot.child("longitude").getValue(Double.class);

                final LatLng location = new LatLng(Latitude, Longitude);
                latLngList.add(location);
                String usercityName = getUserCityName(location);



                MarkerOptions markerOptionsUser = new MarkerOptions();
                markerOptionsUser.position(location);
                markerOptionsUser.title(usercityName);
                markerOptionsUser.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));


                usercurrentLocationMarker = mMap.addMarker(markerOptionsUser);
                markerList.add(usercurrentLocationMarker);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 14F));


                btn_show_directions.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Draw Polyline on Map
                        if (polyline != null) polyline.remove();
                        //Create PolylineOptions
                        PolylineOptions polylineOptions = new PolylineOptions()
                                .addAll(latLngList).clickable(true);
                        polyline = mMap.addPolyline(polylineOptions);

                    }
                });

                btn_clear_directions.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Clear All
                        if (polyline !=null) polyline.remove();
                        //for (Marker marker : markerList) marker.remove();
                        //latLngList.clear();
                        //markerList.clear();

                    }
                });


            }



            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private String getUserCityName(LatLng location) {

        String myCity = "";
        Geocoder geocoder = new Geocoder(DriverMapping.this, Locale.getDefault());
        try {


            List<Address> addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1);

            String address = addresses.get(0).getAddressLine(0);
            myCity = addresses.get(0).getSubLocality();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myCity;
    }

    protected synchronized void buildGoogleApiClient() {
        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        client.connect();
    }


    // paxi ko ho yo


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();


        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
        }


    }


    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;

        mMap.setOnMapLongClickListener(this);


        if (currentLocationMarker != null) {
            currentLocationMarker.remove();
        }
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        String drivercityName = getDriverCityName(latLng);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(drivercityName);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

        currentLocationMarker = mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.moveCamera(CameraUpdateFactory.zoomBy(10));

        if (client != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(client, this);

        }

        // Saving to Firebase Code:

        LocationHelper helper = new LocationHelper( // Creating the helper class (LocationHelper's)
                // Object named helper and storing the current location to that object .

                location.getLongitude(),
                location.getLatitude()
        );
        // storing the helper class data to firebase --> codes

        FirebaseDatabase.getInstance().getReference("Driver Current Location")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(helper).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(DriverMapping.this, "Location is saved ! ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DriverMapping.this, "Location is not saved ! ", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Api use garne suru yaha bata.......YouTube: The code city


  /*      String place1 = latLng.toString();
        String place2 = location.toString();

        String url = getUrl(place1.)*/

    }

    private String getDriverCityName(LatLng latLng) {

        String myCity = "";
        Geocoder geocoder = new Geocoder(DriverMapping.this, Locale.getDefault());
        try {


            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);

            String address = addresses.get(0).getAddressLine(0);
            myCity = addresses.get(0).getSubLocality();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myCity;
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    public void onMapLongClick(LatLng latLng) {


        for (Marker marker : markerList)
            marker.remove();
            latLngList.clear();



    }

}
