package com.lamapress.animeexpo2014.axapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lamapress.animeexpo2014.axapp.R;
import com.lamapress.animeexpo2014.axapp.core.Guest;
import com.lamapress.animeexpo2014.axapp.sqlite_helper.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Anthony on 6/26/2014.
 */
public class GuestFragment extends Fragment {

    private static final String ARG_SECTION = "section_number";
    DatabaseHelper dbHelper = null;
    ArrayList<Guest> m_guestList = new ArrayList<Guest>();

    public static GuestFragment newInstance(int sectionNumber){
        GuestFragment fragment = new GuestFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_SECTION,sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.fragment_panel,container,false);


    }


}
