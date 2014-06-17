package com.lamapress.animeexpo2014.axapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cengalabs.flatui.FlatUI;

/**
 * @author Anthony Lame
 * @version 1.0
 */
public class HomeFragment extends Fragment {

    ViewPager imagePager;

    public static HomeFragment newInstance(int sectionNumber){
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        FlatUI.initDefaultValues(getActivity());
        FlatUI.setDefaultTheme(FlatUI.CANDY);
    }
}
