package com.prince.women_security;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import io.reactivex.functions.Consumer;


public class WomenSecurity extends SettingsActivity  {
    GoogleApiClient googleApiClient;

    protected static final String TAG = "LocationOnOff";


    final static int REQUEST_LOCATION = 199;



    PendingResult<LocationSettingsResult> pendingResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_women_security);
        //===============================================================
//for accesing permissons
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.SEND_SMS, Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.READ_PHONE_STATE) // ask single or multiple permission once
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (granted) {
                            // All requested permissions are granted
                        } else {
                            // At least one permission is denied
                        }
                    }
                });
//======================================================================================
Button whatsapp=findViewById(R.id.button2);
whatsapp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Toast.makeText(WomenSecurity.this, "This Feature will be in new UPDATE", Toast.LENGTH_LONG).show();
    }
});

//==================================================================================================

        Button sharefamily=findViewById(R.id.button);
        sharefamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number1 = null;
                if(number1==null){
                    Intent i=new Intent(getApplicationContext(),SettingsActivity.class);
                    startActivity(i);
                }

                String number2 = null;

                String number3 = null;

                String message = null;


                GpsTracker_for_Sms g = new GpsTracker_for_Sms(getApplicationContext());
                Location l = g.getLocation();
                if (l != null) {
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    number1 = mEditText1.getText().toString();
                    number2 = mEditText2.getText().toString();
                    number3 = mEditText3.getText().toString();
                    message = mEditText4.getText().toString() + "\n\n" + "https://www.google.com/maps/dir/?api=1&destination=" + lat + "," + lon + "&travelmode=driving";
                    Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                            Toast.LENGTH_LONG).show();
                  }

//==========================================================================================
                //Getting intent and PendingIntent instance

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                //Get the SmsManager instance and call the sendTextMessage method to send message

                SmsManager sms = SmsManager.getDefault();

                if (l != null) {
                    sms.sendTextMessage(number1, null, message, pi, null);
                } else if (l != null) {
                    sms.sendTextMessage(number2, null, message, pi, null);
                } else if (l != null) {
                    sms.sendTextMessage(number3, null, message, pi, null);
                } else if (l != null) {
                    sms.sendTextMessage(message, null, message, pi, null);
                }



            }



        } );







}
//====================================================================================================


    //========================================================================================================================
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();      // it inflate the menu item in the code and its xml file will be too inflated
        menuInflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.help) {
            Intent mailIntent = new Intent(Intent.ACTION_VIEW);
            Uri data = Uri.parse("mailto:?subject=" + " Subject of Your Query...  " + "&body=" + " Please Write your Query... " + "&to=" + "pkasaudhan93@gmail.com");
            mailIntent.setData(data);
            startActivity(mailIntent);
            return true;
        } else if (item.getItemId() == R.id.About) {

            Intent intent = new Intent(getApplicationContext(), AboutFeedback.class);
            startActivity(intent);

            return true;
        } else if (item.getItemId() == R.id.Share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out my app at: https://play.google.com/store/apps/details?id=com.google.android.apps.plus");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        }
        else if (item.getItemId() == R.id.setting) {

            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);

        }
        else if (item.getItemId() == R.id.readme) {

            Intent intent = new Intent(getApplicationContext(),OnBoardingActiviity.class);
            startActivity(intent);

        }


        return false;
    }
}
