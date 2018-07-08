package com.noventapp.direct.user.ui.map;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.kml.KmlContainer;
import com.google.maps.android.data.kml.KmlLayer;
import com.google.maps.android.data.kml.KmlPlacemark;
import com.google.maps.android.data.kml.KmlPolygon;
import com.noventapp.direct.user.R;

import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Locale;

public class KmlDemoActivity extends BaseDemoActivity {

    private GoogleMap mMap;
    private String name;
    private static final String TAG = "Hisham";

    protected int getLayoutId() {
        return R.layout.kml_demo;
    }

    public void startDemo() {
        try {
            mMap = getMap();
            retrieveFileFromResource();
//            retrieveFileFromUrl();
        } catch (Exception e) {
            Log.e("Exception caught", e.toString());
        }
    }

    private void retrieveFileFromResource() {
        try {
            KmlLayer kmlLayer = new KmlLayer(mMap, R.raw.map_test, getApplicationContext());

            kmlLayer.addLayerToMap();
            moveCameraToKml(kmlLayer);

//            kmlLayer.setOnFeatureClickListener(new Layer.OnFeatureClickListener() {
//                @Override
//                public void onFeatureClick(Feature feature) {
//                    Toast.makeText(KmlDemoActivity.this,
//                            "Area layer " + name,
//                            Toast.LENGTH_SHORT).show();
//                }
//            });
            kmlLayer.setOnFeatureClickListener(new KmlLayer.OnFeatureClickListener() {
                @Override
                public void onFeatureClick(Feature feature) {
                    Toast.makeText(KmlDemoActivity.this,
                            "Area " + name,
                            Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {

        } catch (XmlPullParserException e) {

        }
    }

    private void retrieveFileFromUrl() {
        new DownloadKmlFile(getString(R.string.kml_url)).execute();
    }

    private void moveCameraToKml(KmlLayer kmlLayer) {
        //Retrieve the first container in the KML layer
        KmlContainer container = kmlLayer.getContainers().iterator().next();
        //Retrieve a nested container within the first container
        container = container.getContainers().iterator().next();
//        container = container.getContainers().iterator().next();
//        container = container.getContainers().iterator().next();
        //Retrieve the first placemark in the nested container
        KmlPlacemark placemark = container.getPlacemarks().iterator().next();
        name = placemark.getProperty("name");

        KmlPolygon polygon = (KmlPolygon) placemark.getGeometry();
        //Create LatLngBounds of the outer coordinates of the polygon
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (LatLng latLng : polygon.getOuterBoundaryCoordinates()) {
            builder.include(latLng);
        }
        mMap.getUiSettings().setZoomControlsEnabled(true);
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        LatLngBounds latLngBounds = builder.build();
        getAddress(this, 32.050739, 35.872391);

        getMap().moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, width, height, 1));
    }

    public static void getAddress(Context context, double latitude, double longitude) {

//Set Address
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && addresses.size() > 0) {


                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

//                Log.d(TAG, "getCountry:  Country :  " + country);
//                Log.d(TAG, "getAddress:  address :  " + address);
//                Log.d(TAG, "getAddress:  city :  " + city);
//                Log.d(TAG, "getAddress:  state :  " + state);
//                Log.d(TAG, "getAddress:  postalCode :  " + postalCode);
//                Log.d(TAG, "getAddress:  knownName :  " + knownName);

                Log.e(TAG, "Addresses: " + addresses.get(0).getAddressLine(0));
                Log.e(TAG, "Addresses getAdminArea()" + " :       " + addresses.get(0).getAdminArea());
                Log.e(TAG, "Addresses getCountryName()" + " :       " + addresses.get(0).getCountryName());
                Log.e(TAG, "Addresses getFeatureName()" + " :       " + addresses.get(0).getFeatureName());
                Log.e(TAG, "Addresses getLocality()" + " :       " + addresses.get(0).getLocality());
                Log.e(TAG, "Addresses getPostalCode()" + " :       " + addresses.get(0).getPostalCode());
                Log.e(TAG, "Addresses getPremises()" + " :       " + addresses.get(0).getPremises());
                Log.e(TAG, "Addresses getSubAdminArea()" + " :       " + addresses.get(0).getSubAdminArea());
                Log.e(TAG, "Addresses getSubLocality()" + " :       " + addresses.get(0).getSubLocality());
                Log.e(TAG, "Addresses getSubThoroughfare()" + " :       " + addresses.get(0).getSubThoroughfare());
                Log.e(TAG, "Addresses getThoroughfare()" + " :       " + addresses.get(0).getThoroughfare());


            }
        } catch (IOException e) {

        }
        return;
    }

    private class DownloadKmlFile extends AsyncTask<String, Void, byte[]> {
        private final String mUrl;

        public DownloadKmlFile(String url) {
            mUrl = url;
        }

        protected byte[] doInBackground(String... params) {
            try {
                InputStream is = new URL(mUrl).openStream();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int nRead;
                byte[] data = new byte[16384];
                while ((nRead = is.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }
                buffer.flush();
                return buffer.toByteArray();
            } catch (IOException e) {

            }
            return null;
        }

        protected void onPostExecute(byte[] byteArr) {
            try {
                KmlLayer kmlLayer = new KmlLayer(mMap, new ByteArrayInputStream(byteArr),
                        getApplicationContext());
                kmlLayer.addLayerToMap();

                kmlLayer.setOnFeatureClickListener(new KmlLayer.OnFeatureClickListener() {
                    @Override
                    public void onFeatureClick(Feature feature) {
                        Toast.makeText(KmlDemoActivity.this,
                                "Feature clicked: " + feature.getId(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
                moveCameraToKml(kmlLayer);
            } catch (XmlPullParserException e) {

            } catch (IOException e) {

            }
        }
    }
}
