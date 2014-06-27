package com.lamapress.animeexpo2014.axapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lamapress.animeexpo2014.axapp.R;

import org.w3c.dom.Text;

/**
 * Created by Anthony on 6/24/2014.
 */
public class TempFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        View mainView = inflater.inflate(R.layout.list_item_navigation_drawer,container,false);
        TextView view = (TextView)mainView.findViewById(R.id.navigation_text);

        view.setText("This is a test fragment, nothing is here at the moment *shrug*");

        return mainView;
    }

    public static TempFragment newInstance(int sectionNumber){
        TempFragment fragment = new TempFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
}
