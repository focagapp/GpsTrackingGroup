package com.focaga.gpstrackinggroup;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.focaga.gpstrackinggroup.MapsActivity.mMap;

/**
 * Created by matteocarlessi on 12/09/17.
 */

public class MyCurrentLocationListener implements LocationListener {
    @Override
    public void onLocationChanged(Location location) {
        CameraUpdate center= CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(12);

        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 1000, null);
        // Add a marker in Sydney, Australia, and move the camera.
        LatLng dalmine = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(dalmine).title("Marker in dalmine"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(dalmine));


        String myLocation = "Latitude = " + location.getLatitude() + " Longitude = " + location.getLongitude();

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
