package com.example.shivanipatel.cityguide;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * Created by shivani.patel on 06-04-2016.
 */
public class HomePage extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    FloatingActionButton floatingActionButton;
    EditText etLocation;
    Button btnLocation;

    private static final String TAG = MainActivity.class.getSimpleName();
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    Location location;
    GoogleApiClient googleApiClient;
    boolean requestingLocationUpdates = false;
    LocationRequest locationRequest;

    private static int UPDATE_INTERVAL = 10000;  //10sec
    private static int FASTEST_INTERVAL = 5000;  //5sec
    private static int DISPLACEMENT = 10; //10m


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        floatingActionButton=(FloatingActionButton)findViewById(R.id.btnFab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryPage.class);
                startActivity(intent);
            }
        });


        if (checkPlayServices()) {

            // Building the GoogleApi client
            buildGoogleApiClient();
            createLocationRequest();
        }

        etLocation=(EditText)findViewById(R.id.etLocation);
        btnLocation=(Button)findViewById(R.id.btnLocation);
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayLocation();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPlayServices();
        if(googleApiClient.isConnected() && requestingLocationUpdates){
            startLocationUpdates();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    private void displayLocation() {
        location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            etLocation.setText(latitude + ", " + longitude);

        } else {

            etLocation.setText("(Couldn't get the location. Make sure location is enabled on the device)");
        }
    }

    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }

    /**
     * Creating location request object
     * */
    protected void createLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setSmallestDisplacement(DISPLACEMENT);
    }

    /**
     * Method to verify google play services on the device
     * */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(getApplicationContext(), "This device is not supported.", Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
        return true;
    }

    protected void startLocationUpdates() {


        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);

    }

    /**
     * Stopping location updates
     */
    protected void stopLocationUpdates()
    {

        LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
    }


    @Override
    public void onConnected(Bundle bundle) {
        if(requestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        googleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(getApplicationContext(), "Location changed!", Toast.LENGTH_SHORT).show();
        // Displaying the new location on UI
        displayLocation();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){


            int id =item.getItemId();

            if(id == R.id.spinner)
            {
                return true;
            }
            if (id == R.id.Bangalore) {
                etLocation.setText("Bangalore");
                return  true;
            }
            if (id == R.id.Mumbai) {
                etLocation.setText("Mumbai");
                return  true;
            }

             if (id == R.id.Pune) {
                 etLocation.setText("Pune");
                 return  true;
            }
            if (id == R.id.Kolkata) {
                etLocation.setText("Kolkata");
                return  true;
            }
             if (id == R.id.Chennai) {
                 etLocation.setText("Chennai");
                 return  true;
            }
            if (id == R.id.Delhi) {
                etLocation.setText("Delhi");
                return  true;
            }
            if (id == R.id.Jaipur) {
                etLocation.setText("Jaipur");
                return  true;
            }
            if (id == R.id.Bhopal) {
                etLocation.setText("Bhopal");
                return  true;
            }
        if (id == R.id.Raipur) {
            etLocation.setText("Raipur");
            return  true;
        }
        if (id == R.id.Patna) {
            etLocation.setText("Patna");
            return  true;
        }

        return super.onOptionsItemSelected(item);
    }


}