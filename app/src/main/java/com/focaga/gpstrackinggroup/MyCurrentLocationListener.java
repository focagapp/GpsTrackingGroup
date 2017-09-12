package com.focaga.gpstrackinggroup;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.focaga.gpstrackinggroup.MapsActivity.group;
import static com.focaga.gpstrackinggroup.MapsActivity.mMap;
import static com.focaga.gpstrackinggroup.MapsActivity.user;

/**
 * Created by matteocarlessi on 12/09/17.
 */

public class MyCurrentLocationListener implements LocationListener {

    int count=0;
    @Override
    public void onLocationChanged(Location location) {
        if (count==10) {
            CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
            //CameraUpdate zoom=CameraUpdateFactory.zoomTo(12);

            mMap.moveCamera(center);
            //mMap.animateCamera(zoom);
            //mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 1000, null);
            // Add a marker in Sydney, Australia, and move the camera.
            LatLng dalmine = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions().position(dalmine).title("Marker in dalmine"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(dalmine));

            try {
                URL paginaURL = new URL("http://gpstrackinggroup.altervista.org/insert.php?lat=" + location.getLatitude() +
                        "&lon=" + location.getLongitude() + "&usr=" + user + "&gro=" + group);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String myLocation = "Latitude = " + location.getLatitude() + " Longitude = " + location.getLongitude();
            count=0;
        } else {
            count ++;
        }

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
