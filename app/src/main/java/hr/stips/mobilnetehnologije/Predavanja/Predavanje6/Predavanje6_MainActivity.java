package hr.stips.mobilnetehnologije.Predavanja.Predavanje6;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import hr.stips.mobilnetehnologije.R;

/*
* ispis lokacije
* */

public class Predavanje6_MainActivity extends AppCompatActivity {

    private static final int FINE_LOCATION_REQUEST_IDENTIFIER = 101;//identifikacija zahtjeva (popup-a) za dopuštenja - proizvoljan naziv kao i broj

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p6_activity_main);

        //prilikom ucitavanja main activity-a
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_WIFI_STATE},//lista stringova koji ce se prikazati na popup-u
                    FINE_LOCATION_REQUEST_IDENTIFIER);
        } else {
            TextView textView = findViewById(R.id.permissions_message);
            textView.setText("Prava su vec dana!!!");
            setupLocationTracking();
        }
    }


    //poziva se prilikom dozvole/nedozvole prava
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case FINE_LOCATION_REQUEST_IDENTIFIER: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    TextView textView = findViewById(R.id.permissions_message);
                    textView.setText("Prava su upravo dana!!!");
                    setupLocationTracking();
                } else {
                    TextView textView = findViewById(R.id.permissions_message);
                    textView.setText("Prava nisu dana!!!");
                }

            }
            return;
        }

    }

    @SuppressLint("MissingPermission")
    private void setupLocationTracking() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {//način kako instancirati apstraktnu klasu implemetirajući apstraktne metode na licu mjesta
            @Override
            public void onLocationChanged(Location location) {
                TextView textView = findViewById(R.id.textView);
                Double latitude = location.getLatitude();
                Double longitude = location.getLongitude();
                textView.setText(Double.toString(latitude) + "  " + Double.toString(longitude));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };


        // Register the listener with the Location Manager to receive location updates
        //povezivanje Location Listener-a i Location Managera
        //koliko cesto ce se pozivati fcja onLocationChange: drugi argument je broj sekundi, treći argument je udaljnost (u metrima) koju je uredjaj presao
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 100, locationListener);

    }

}
