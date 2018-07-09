package com.noventapp.direct.user.ui.address;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.noventapp.direct.user.R;
import com.noventapp.direct.user.utils.MapUtil;

import java.io.IOException;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressMapActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    @BindView(R.id.iv_moving)
    AppCompatImageView ivMoving;
    @BindView(R.id.atv_placeSearch)
    AppCompatAutoCompleteTextView atvPlaceSearch;
    Marker marker;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private Geocoder geocoder;
    private double latitude, longitude;
    private SupportMapFragment mapFragment;
    private int addressId = 0;
    private double addressLat, addressLng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_map);
        ButterKnife.bind(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //need to get the address
        geocoder = new Geocoder(this, Locale.getDefault());
        getIntentData();

    }

    private void getIntentData() {
        if (getIntent().getExtras() != null) {
            addressId = getIntent().getExtras().getInt("address_id");
            addressLat = getIntent().getExtras().getDouble("address_lat");
            addressLng = getIntent().getExtras().getDouble("address_lng");
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_COARSE_LOCATION}, 200);
        }
        mMap.setMyLocationEnabled(true);
        //Initialize Google Play Services
        buildGoogleApiClient();
        mGoogleApiClient.connect();

        mMap.setOnCameraIdleListener(() -> {
            LatLng center = mMap.getCameraPosition().target;

            if (marker != null) {
                mMap.addMarker(new MarkerOptions().position(center).
                        icon(MapUtil.bitmapDescriptorFromVector(AddressMapActivity.this, R.drawable.ic_marker)));
                try {
                    atvPlaceSearch.setText(geocoder.getFromLocation(latitude, longitude, 1)
                            .get(0).getAddressLine(0));
                } catch (IOException e) {
                }


            }

        });
        mMap.setOnCameraChangeListener(cameraPosition -> {
            atvPlaceSearch.setText("");
            marker.remove();
            mMap.clear();
        });

    }


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (addressLng != 0.0 && addressLat != 0.0) {
            marker = mMap.addMarker(new MarkerOptions().position(new LatLng(addressLat, addressLng)).
                    icon(MapUtil.bitmapDescriptorFromVector(this, R.drawable.ic_marker)).draggable(true));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(addressLat, addressLng), 15));
            latitude = addressLat;
            longitude = addressLng;
        } else {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (mLastLocation != null) {
                latitude = mLastLocation.getLatitude();
                longitude = mLastLocation.getLongitude();
                marker = mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).
                        icon(MapUtil.bitmapDescriptorFromVector(this, R.drawable.ic_marker)).draggable(true));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15));
            }
        }


    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    public void findPlace() {
        try {
            AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                    .setCountry("SA")
                    .build();

            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).setFilter(typeFilter)
                    .build(AddressMapActivity.this);

            startActivityForResult(intent, 1);
        } catch (GooglePlayServicesRepairableException e) {
        } catch (GooglePlayServicesNotAvailableException e) {
        }
    }

    // A place has been received; use requestCode to track the request.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // retrive the data by using getPlace() method.
                Place place = PlaceAutocomplete.getPlace(AddressMapActivity.this, data);
                Log.e("Tag", "Place: " + place.getAddress() + place.getPhoneNumber());
                atvPlaceSearch.setText(place.getName() + "," + place.getAddress());
                longitude = place.getLatLng().longitude;
                latitude = place.getLatLng().latitude;
                mMap.clear();
                LatLng position = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(position).
                        icon(MapUtil.bitmapDescriptorFromVector(AddressMapActivity.this, R.drawable.ic_marker)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15));

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(AddressMapActivity.this, data);
                Log.e("Tag", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    @OnClick({R.id.btn_selectLocation, R.id.btn_back, R.id.atv_placeSearch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_selectLocation:
                Intent intent = new Intent(this, AddressInformationActivity.class);
                intent.putExtra("lat", latitude);
                intent.putExtra("lng", longitude);
                intent.putExtra("address_id", addressId);
                startActivity(intent);
                break;
            case R.id.btn_back:
                onBackPressed();
                break;
            case R.id.atv_placeSearch:
                findPlace();
                break;
        }
    }


}
