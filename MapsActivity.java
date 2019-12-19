package com.example.hairmall2;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GoogleMap mMap2;
    private GoogleMap mMap3;
    private GoogleMap mMap4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap2 = googleMap;
        mMap3 = googleMap;
        mMap4 = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng Xino2 = new LatLng(37.496858, 126.952923);
        LatLng LeeAn = new LatLng(37.495320, 126.957156);
        LatLng  Lucas = new LatLng(37.497092, 126.954291);
        LatLng seungChol = new LatLng(37.506037, 126.946921);
        mMap.addMarker(new MarkerOptions().position(Xino2).title("2th Xino Hair "));
        mMap2.addMarker(new MarkerOptions().position(LeeAn).title("Lee An Hair"));
        mMap3.addMarker(new MarkerOptions().position(Lucas).title("Lucas"));
        mMap4.addMarker(new MarkerOptions().position(seungChol).title("Seung Chol"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Xino2));

        mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener(){
            @Override
            public void onInfoWindowClick(Marker marker){
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel:028123493"));

                if(intent1.resolveActivity(getPackageManager())!= null){
                    startActivity(intent1);
                }
            }
        });

        mMap2.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener(){
            @Override
            public void onInfoWindowClick(Marker marker){
                Intent intent2 = new Intent(Intent.ACTION_DIAL);
                intent2.setData(Uri.parse("tel:0212345678"));

                if(intent2.resolveActivity(getPackageManager())!= null){
                    startActivity(intent2);
                }
            }
        });

        mMap3.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener(){
            @Override
            public void onInfoWindowClick(Marker marker){
                Intent intent3 = new Intent(Intent.ACTION_DIAL);
                intent3.setData(Uri.parse("tel:0312345678"));

                if(intent3.resolveActivity(getPackageManager())!= null){
                    startActivity(intent3);
                }
            }
        });

        mMap4.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener(){
            @Override
            public void onInfoWindowClick(Marker marker){
                Intent intent4 = new Intent(Intent.ACTION_DIAL);
                intent4.setData(Uri.parse("tel:0412345678"));

                if(intent4.resolveActivity(getPackageManager())!= null){
                    startActivity(intent4);
                }
            }
        });
    }
}
