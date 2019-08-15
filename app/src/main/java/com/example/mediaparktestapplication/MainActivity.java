package com.example.mediaparktestapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.mediaparktestapplication.adapters.RecyclerAdapter;
import com.example.mediaparktestapplication.models.Car;
import com.example.mediaparktestapplication.viewmodels.CarViewModel;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.patloew.rxlocation.RxLocation;
import com.tbruyelle.rxpermissions2.RxPermissions;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private RxPermissions rxPermissions;
    private RxLocation rxLocation;
    private Disposable rxLocationObserver;
    private static final LatLng AKROPOLIS = new LatLng(54.7101, 25.2620);

    private static LatLng currentLocation = AKROPOLIS;
    private Marker currentLocationMarker;

    private GoogleMap mMap;

    private RecyclerAdapter mAdapter;
    private CarViewModel mCarViewModel;

    private RecyclerView mRecyclerView;
    private LinearLayout mMapLayout;
    private LinearLayout mFilterLayout;

    private EditText plateFilter;
    private EditText batteryFilter;

    public static LatLng getCurrentLocation(){
        return currentLocation;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_map:
                    mMapLayout.setVisibility(View.VISIBLE);
                    mFilterLayout.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_list:
                    mMapLayout.setVisibility(View.GONE);
                    mFilterLayout.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_filters:
                    mMapLayout.setVisibility(View.GONE);
                    mFilterLayout.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        rxPermissions = new RxPermissions(this);
        rxLocation = new RxLocation(this);

        getPermissions();

        mFilterLayout = findViewById(R.id.filter_layout);
        mMapLayout = findViewById(R.id.map_layout);
        mRecyclerView = findViewById(R.id.recycler_view);

        mCarViewModel = ViewModelProviders.of(this).get(CarViewModel.class);
        mCarViewModel.init();
        observeViewModel(mCarViewModel);
        initRecyclerView();
        initFilters();
    }

    private void observeViewModel(CarViewModel viewModel){
        viewModel.getCars().observe(this, new Observer<List<Car>>() {
            @Override
            public void onChanged(@Nullable List<Car> cars) {
                if (cars != null) {
                    mAdapter.notifyDataSetChanged();
                    mAdapter.setCars(cars);
                    loadMap();
                }
            }
        });
    }

    // Permissions
    private void getPermissions(){
        rxPermissions
                .request(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(granted -> {
                    if (granted) {
                        getLocation();
                    } else {
                        // TODO Proper error handling
                        Toast toast = Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG);
                        toast.show();
                        getLocation();
                    }
                }).dispose();
    }

    // Location
    private void getLocation(){

        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(5000);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // If there's no current location, it's set to AKROPOLIS Vilnius
            if (currentLocation == null) currentLocation = AKROPOLIS;
        }
        rxLocationObserver = rxLocation.location()
                .updates(locationRequest)
                .flatMap(location -> rxLocation.geocoding().fromLocation(location).toObservable())
                .subscribe(address -> {
                    currentLocation = new LatLng(address.getLatitude(), address.getLongitude());
                    if (mMap != null) {
                        if (currentLocationMarker != null) currentLocationMarker.remove();
                        currentLocationMarker = mMap.addMarker(new MarkerOptions().position(currentLocation)
                                .title("Current Location")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                    }
                });
    }

    // Map
    private void loadMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(AKROPOLIS, 11.0f));
        mMap.clear();
        LatLng marker;
        for (Car car : mCarViewModel.getCars().getValue()) {
            marker = new LatLng(car.getLocation().getLatitude(), car.getLocation().getLongitude());
            mMap.addMarker(new MarkerOptions().position(marker).title(car.getPlateNumber()));
        }
    }

    // List
    private void initRecyclerView(){
        mAdapter = new RecyclerAdapter(this, mCarViewModel.getCars().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    // Filters
    private void initFilters(){

        plateFilter = findViewById(R.id.plate_filter);
        plateFilter.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                    batteryFilter.setText("");
                filterByPlate(plateFilter.getText().toString());
            }
        });
        batteryFilter = findViewById(R.id.battery_filter);
        batteryFilter.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                    plateFilter.setText("");
                filterByPlate(plateFilter.getText().toString());
                if (!batteryFilter.getText().toString().isEmpty())
                    filterByBattery(batteryFilter.getText().toString());
            }
        });
    }

    private void filterByPlate(String plateString){
        List<Car> result = mCarViewModel.getCars().getValue()
                .stream().filter(car -> car.getPlateNumber().contains(plateString))
                .collect(Collectors.toList());
        mAdapter.notifyDataSetChanged();
        mAdapter.setCars(result);
        loadMap();
    }

    private void filterByBattery(String filterString){
        List<Car> result = mCarViewModel.getCars().getValue()
                .stream().filter(car -> car.getBatteryPercentage() >= Integer.parseInt(filterString))
                .collect(Collectors.toList());
        mAdapter.notifyDataSetChanged();
        mAdapter.setCars(result);
        loadMap();

    }
}