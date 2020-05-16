package com.prince.women_security;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    protected static final String TAG = "LocationOnOff";
    final int SPLASH_DISPLAY_LENGTH = 1500;
long l=9000;
int i=9000;

    private GoogleApiClient googleApiClient;
    final static int REQUEST_LOCATION = 199;

    @Override
      protected void onCreate(Bundle savedInstanceState)
      {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //===================================================================================









            /* New Handler to start the Menu-Activity
             * and close this Splash-Screen after some seconds.*/
            new Handler().postDelayed(new Runnable()
            {


                @Override
                public void run()
                {

                    Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                            .getBoolean("isFirstRun", true);

                    if (isFirstRun) {
                        //show start activity

                        startActivity(new Intent(MainActivity.this, OnBoardingActiviity.class));
                        Toast.makeText(MainActivity.this, "First Run", Toast.LENGTH_LONG)
                                .show();
                        try {
                            wait(l);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        startActivity(new Intent(MainActivity.this, locationeanble.class));

                    }

                    getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                            .putBoolean("isFirstRun", false).commit();


                    // Todo Location Already on  ... start




                }
            }, SPLASH_DISPLAY_LENGTH);


    }


       }