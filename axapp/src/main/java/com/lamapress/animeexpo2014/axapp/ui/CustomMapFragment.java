package com.lamapress.animeexpo2014.axapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.lamapress.animeexpo2014.axapp.R;


/**
 */
public class CustomMapFragment extends Fragment {

    public static final String ARG_SECTION = "section_number";

    private static GoogleMap googleMap;

    public static CustomMapFragment newInstance(int sectionNumber){
        CustomMapFragment mapFragment = new CustomMapFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION,sectionNumber);
        mapFragment.setArguments(args);
        return mapFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle onSavedInstanceState){

        // PLACEHOLDER
        View rootView = inflater.inflate(R.layout.fragment_location,container,false);

        return rootView;
    }
}
