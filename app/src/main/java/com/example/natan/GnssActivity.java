package com.example.natan;

/*
* Jorge (nosso prefeito) resolveu o problema do import no manifest
 */
import android.Manifest;
import android.content.pm.PackageManager;


import android.location.GnssStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.content.pm.PackageManager;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GnssActivity extends AppCompatActivity {



    private LocationManager locationManager;
    private LocationProvider locationProvider;
    private static final int REQUEST_LOCATION = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gnss);
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        obtemLocationProvider_Permission();
    }




    public void obtemLocationProvider_Permission() {

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {

            locationProvider = locationManager.getProvider(LocationManager.GPS_PROVIDER);

        } else {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if (requestCode == REQUEST_LOCATION) {
            if(grantResults.length == 1 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {

                obtemLocationProvider_Permission();
            }
            else {

                Toast.makeText(this,"Sem permissão para acessar o sistema de posicionamento",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public void obtemLocationProvider_Autorizacao() {

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {

            locationProvider = locationManager.getProvider(LocationManager.GPS_PROVIDER);

        } else {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
        }
    }


    public void startLocationAndGNSSUpdates() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            return;}
        locationManager.requestLocationUpdates(locationProvider.getName(), 1000, 0.1f
                , new LocationListener() {
                    @Override
                    public void onLocationChanged(@NonNull Location location) {
                        mostraLocation(location);
                    }
                });

    }

    public void mostraLocation(Location location) {
        TextView textView=findViewById(R.id.textviewLocation_id);
        String mens="Dados da Última posição\n";
        if (location!=null) {
            mens+=String.valueOf("Latitude(graus)="
                    +Location.convert(location.getLatitude(),Location.FORMAT_SECONDS))+"\n"
                    +String.valueOf("Longitude(graus)="
                    +Location.convert(location.getLongitude(),Location.FORMAT_SECONDS))+"\n"
                    +String.valueOf("Velocidade(m/s)="+location.getSpeed())+"\n"
                    +String.valueOf("Rumo(graus)="+location.getBearing());
        }
        else {
            mens+="Localização Não disponível";
        }
        textView.setText(mens);
    }

    
    public void onSatelliteStatusChanged(@NonNull GnssStatus status) {
        TextView tv_gnss=(TextView)findViewById(R.id.textviewLocation_id);
        String mens="Dados do Sitema de Posicionamento\n";
        if (status!=null) {
            mens+="Número de Satélites:"+status.getSatelliteCount()+"\n";
            for(int i=0;i<status.getSatelliteCount();i++) {
                mens+="SVID="+status.getSvid(i)+"-"+status.getConstellationType(i)+
                        "Azi="+status.getAzimuthDegrees(i)+

                        "Elev="+status.getElevationDegrees(i)+ "X|";

            }
        }
        else {
            mens+="GNSS Não disponível";
        }
        tv_gnss.setText(mens);
    }




    //Falta Setar o ID de Activity de text view e checar se o layout ta correto
    //
}