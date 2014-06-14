package com.lamapress.animeexpo2014.axapp.ui;


import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cengalabs.flatui.FlatUI;
import com.cengalabs.flatui.views.FlatButton;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.lamapress.animeexpo2014.axapp.HomeScreen;
import com.lamapress.animeexpo2014.axapp.R;
import com.lamapress.animeexpo2014.axapp.core.Panel;
import com.lamapress.animeexpo2014.axapp.sqlite_helper.DatabaseHelper;

import org.w3c.dom.Text;

import java.lang.reflect.AccessibleObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Anthony on 6/14/2014.
 */
public class PanelFragment extends Fragment {

    public static final String ARG_SECTION = "section_number";

    DatabaseHelper dbHelper = null;

    List<Panel> panelList= new ArrayList<Panel>();
    TextView textView;



    public PanelFragment(){

    }

    public static PanelFragment newInstance(int sectionNumber){
        PanelFragment fragment = new PanelFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION,sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public void onDestroy(){
        super.onDestroy();
        if(dbHelper != null ){
            OpenHelperManager.releaseHelper();
            dbHelper = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){
        FlatUI.initDefaultValues(getActivity());
        FlatUI.setDefaultTheme(FlatUI.CANDY);

        View rootView = inflater.inflate(R.layout.fragment_home_screen,container,false);

        FlatButton testButton = (FlatButton)rootView.findViewById(R.id.test_button);
        FlatButton viewButton = (FlatButton)rootView.findViewById(R.id.show_button);
        textView = (TextView)rootView.findViewById(R.id.test_text_view);


        testButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                StringBuilder stringBuilder = new StringBuilder();

                try {
                    Dao<Panel,String> panelDao = getHelper().getPanelDao();
                    panelList = panelDao.queryForAll();
                    GregorianCalendar cal = new GregorianCalendar();

                    for(int i = 0;i < panelList.size(); i++){
                        stringBuilder.append(panelList.get(i).getM_sPanelName()+ "\n");
                        stringBuilder.append(panelList.get(i).getM_sPanelDescription() + "\n");
                        stringBuilder.append(panelList.get(i).getM_sPanelRoom()+ "\n");
                        stringBuilder.append(panelList.get(i).getM_sPanelType()+ "\n");
                                cal.setTimeInMillis(panelList.get(i).m_PanelTime);
                        stringBuilder.append(cal.get(Calendar.MONTH) + " " + cal.get(Calendar.DAY_OF_MONTH) + "\n");


                    }
                }
                catch(SQLException se){
                    stringBuilder.append("WHAT THE FUCK");
                }

                textView.setText(stringBuilder.toString());
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                RestAdapter rest = new RestAdapter.Builder()
                        .setEndpoint(getActivity().getString(R.string.deployd_server_ip) + ":" + getActivity()
                                .getString(R.string.deployd_server_port))
                        .build();


                Panel.PanelService panelService = rest.create(Panel.PanelService.class);
                panelService.listItems(
                        new Callback<List<Panel>>() {
                            @Override
                            public void success(List<Panel> panels, Response response) {
                                try{
                                    Dao<Panel,String> panelDao = getHelper().getPanelDao();
                                    for(int i = 0;i < panels.size();i++){
                                        panelDao.createOrUpdate(panels.get(i));
                                    }
                                }
                                catch(SQLException se){

                                }
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Log.v("FYI", "Panel inflation error @ server: " + error.getUrl());
                                Log.v("FYI","Error: " + error.toString());
                            }
                        }
                );
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        ((HomeScreen) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION)
        );
    }

    public DatabaseHelper getHelper(){
        if(dbHelper == null){
            dbHelper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        }
        return dbHelper;
    }
}
