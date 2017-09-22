package com.focaga.gpstrackinggroup;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.*;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.util.EntityUtils;

import static com.focaga.gpstrackinggroup.MapsActivity.group;
import static com.focaga.gpstrackinggroup.MapsActivity.mMap;
import static com.focaga.gpstrackinggroup.MapsActivity.user;

/**
 * Created by matteocarlessi on 12/09/17.
 */

public class MyCurrentLocationListener implements LocationListener {
    long runTime;
    long start=-1;
    String result;
    AsyncHttpClient client = new AsyncHttpClient(); //http://loopj.com/android-async-http/
    @Override
    public void onLocationChanged(Location location) {
        if (start == -1) {
            start = System.currentTimeMillis();
        }
        runTime = System.currentTimeMillis() - start;
        if (runTime >= 5000) {
            //CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
            //CameraUpdate zoom=CameraUpdateFactory.zoomTo(12);
            //mMap.moveCamera(center);

            //mMap.animateCamera(zoom);
            //mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 1000, null);
            // Add a marker in Sydney, Australia, and move the camera.
           /* LatLng newPos = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions().position(newPos).title("Marker in dalmine"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(newPos));*/

            try {
                final String paginaURL = "http://gpstrackinggroup.altervista.org/insert.php?lat=" + location.getLatitude() +
                        "&lon=" + location.getLongitude() + "&usr=" + user + "&gro=" + group;
                client.get(paginaURL, new TextHttpResponseHandler() {

                    @Override
                    public void onStart() {
                        // called before request is started
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        // called when response HTTP status is "200 OK"
                        System.out.println("=====RESULT======"+responseString);
                        aggiornaPosizioni(responseString);


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    }

                    @Override
                    public void onRetry(int retryNo) {
                        // called when request is retried
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            start = -1 ;
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

    public void aggiornaPosizioni(String posizioni) {
        String[] data = new String[4];
        StringTokenizer stringTokenizer = new StringTokenizer(posizioni,"&");
        while (stringTokenizer.hasMoreTokens()) {
            for (int i = 0; i < 4 && stringTokenizer.hasMoreTokens(); i++) {
                data[i] = stringTokenizer.nextToken();
                System.out.println(stringTokenizer.countTokens());

            }
            LatLng pos = new LatLng(Double.parseDouble(data[0]), Double.parseDouble(data[1]));
            mMap.addMarker(new MarkerOptions().position(pos).title(data[2] + " - " + data[3]));
        }
    }
}

