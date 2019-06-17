package com.example.tests1;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_LOCATION = 1;
    Button button;
    TextView textView;
    LocationManager locationManager;
    String lattitude,longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        textView = (TextView)findViewById(R.id.text_location);
        button = (Button)findViewById(R.id.button_location);

        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)


    {

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))

            {
                buildAlertMessageNoGps();
            }


        else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            {
                getLocation();


            ////ye badiya chalne wala h
                //
                //
                //
                // ((TextView)findViewById(R.id.check2)).setText("NEARBY PARKING" + "\n" + "MAC CENTER         -distance" +  "\n" + "11th BLOCK" + "\n" +  "IT BLOCK" );


                ((TextView)findViewById(R.id.check2)).setVisibility(View.VISIBLE);
                ((TextView)findViewById(R.id.check3)).setVisibility(View.VISIBLE);
                ((TextView)findViewById(R.id.check4)).setVisibility(View.VISIBLE);
                ((TextView)findViewById(R.id.check5)).setVisibility(View.VISIBLE);

            }




    }





/*       public void onClick(View view) {

            Intent intent;

            switch(v.getId()) {
                case R.id.check2: // R.id.textView1
                    intent = new Intent(this, Second.class);
                    break;
                case R.id.check3: // R.id.textView2
                    intent = new Intent(this, Third.class);
                    break;
                case R.id.check4: // R.id.textView3
                    intent = new Intent();
            }

            startActivity(intent);
        }

*/

    public void serve(View v)
    {
        Intent i=new Intent();
        i.setClass(this,Second.class);
        startActivity(i);
    }

    public void form(View v)
    {
        Intent i=new Intent();
        i.setClass(this,Third.class);
        startActivity(i);
    }

    public void comp(View v)
    {
        Intent i=new Intent();
        i.setClass(this,Fourth.class);
        startActivity(i);
    }



    /// just to get the damn location


    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Location location2 = locationManager.getLastKnownLocation(LocationManager. PASSIVE_PROVIDER);

            if (location != null) {
                double latti = location.getLatitude();
                double longi = location.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                textView.setText("Your current location is"+ "\n" + "Lattitude = " + lattitude
                        + "\n" + "Longitude = " + longitude);


            } else  if (location1 != null) {
                double latti = location1.getLatitude();
                double longi = location1.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                textView.setText("Your current location is"+ "\n" + "Lattitude = " + lattitude
                        + "\n" + "Longitude = " + longitude);


            } else  if (location2 != null) {
                double latti = location2.getLatitude();
                double longi = location2.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                textView.setText("Your current location is"+ "\n" + "Lattitude = " + lattitude
                        + "\n" + "Longitude = " + longitude);

            }else{

                Toast.makeText(this,"Unble to Trace your location",Toast.LENGTH_SHORT).show();

            }
        }

    }

    /////location ends here boi



    /////just to show switch on the gps noob
    protected void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }




    //// alert ends here boi





}
