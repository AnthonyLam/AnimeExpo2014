package com.lamapress.animeexpo2014.axapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.lamapress.animeexpo2014.axapp.R;


/**
 */
public class CustomMapFragment extends Fragment{

    public static final String ARG_SECTION = "section_number";

    private static GoogleMap googleMap;

    public static CustomMapFragment newInstance(int sectionNumber){
        CustomMapFragment mapFragment = new CustomMapFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION,sectionNumber);
        return mapFragment;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_location);

        googleMap = ((SupportMapFragment) this.getSupportFragmentManager().findFragmentById(R.id.maps)).getMap();
    }

}
