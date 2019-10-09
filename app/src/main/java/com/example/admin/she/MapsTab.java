package com.example.admin.she;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;
import java.util.Map;

public class MapsTab extends Fragment implements OnMapReadyCallback {


    private GoogleMap mMap;


    public double latitude;
    public double longitude;
    public LocationManager locationManager;
    public Criteria criteria;
    public String bestProvider;

    LocationManager mLocationManager;
    Location mLocation;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_maps, container, false);
        return view;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment_map);

        if(mapFragment != null){
            Log.d("debug","Error occured before this");
            mapFragment.getMapAsync(this);
            //Toast.makeText(getContext(),"Loading maps succesfully.",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(),"Map could not be loaded",Toast.LENGTH_SHORT).show();
        }


        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);


        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(getContext(),"Inside Wierd",Toast.LENGTH_SHORT).show();

            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        getLocation();

//
//        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        longitude = location.getLongitude();
//        latitude = location.getLatitude();
//
//        if(location == null){
//
//            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//            builder.setTitle("Location NULL");
//            builder.setMessage("Location could not be determined.");
//            builder.show();
//
//            Toast.makeText(getContext(),"Location could not be determined.",Toast.LENGTH_SHORT).show();
//
//
//        }
//        else{
//
//             final LocationListener locationListener = new LocationListener() {
//                @Override
//                public void onLocationChanged(Location location) {
//                    longitude = location.getLongitude();
//                    latitude = location.getLatitude();
//                    UserPosition.currentLatitude = latitude;
//                    UserPosition.currentLongitude = longitude;
//                }
//
//                @Override
//                public void onStatusChanged(String provider, int status, Bundle extras) {
//
//                }
//
//                @Override
//                public void onProviderEnabled(String provider) {
//
//                }
//
//                @Override
//                public void onProviderDisabled(String provider) {
//
//                }
//            };
//
//
//            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);
//
//
//        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        LatLng position = new LatLng(23.0225, 72.5714);
        mMap.addMarker(new MarkerOptions().position(position).title("Marker at current location."));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position));

    }
    public static boolean isLocationEnabled(Context context)
    {
        //...............
        return true;
    }

    protected void getLocation() {
        if (isLocationEnabled(getContext())) {
            locationManager = (LocationManager)  getActivity().getSystemService(Context.LOCATION_SERVICE);
            criteria = new Criteria();

            bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true)).toString();


            if (ContextCompat.checkSelfPermission( getContext(),android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED )
            {
               Toast.makeText(getContext(),"Permission not granted",Toast.LENGTH_SHORT).show();
            }else{
                mLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }


            if (mLocation != null) {
                Log.e("TAG", "GPS is on");
                latitude = mLocation.getLatitude();
                longitude = mLocation.getLongitude();
                Toast.makeText(getContext(), "latitude:" + latitude + " longitude:" + longitude, Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getContext(), "Location is still NULL", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getContext(),"Either Location Services not enabled on your Android device OR permissions not granted",Toast.LENGTH_SHORT).show();
        }
    }


}
