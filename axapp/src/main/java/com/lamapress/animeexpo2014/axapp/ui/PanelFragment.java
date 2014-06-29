package com.lamapress.animeexpo2014.axapp.ui;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.media.Rating;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.lamapress.animeexpo2014.axapp.R;
import com.lamapress.animeexpo2014.axapp.core.Panel;
import com.lamapress.animeexpo2014.axapp.sqlite_helper.DatabaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardListView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Anthony on 6/22/2014.
 */
public class PanelFragment extends Fragment {
    public static final String ARG_SECTION = "section_number";

    DatabaseHelper dbHelper = null;
    List<Panel> panelList = new ArrayList<Panel>();


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
        if(dbHelper != null){
            OpenHelperManager.releaseHelper();
            dbHelper = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_panel,container,false);

        try{
            initCard();
        }
        catch(NullPointerException npe){
            RestAdapter rest = new RestAdapter.Builder()
                    .setEndpoint(getActivity().getString(R.string.deployd_server_ip) + ":" +
                     getActivity().getString(R.string.deployd_server_port))
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
                                initCard();
                            }
                            catch(SQLException se){

                            }
                        }
                        @Override
                        public void failure(RetrofitError error) {
                            Log.v("FYI", "Panel inflation error @ server: " + error.getUrl());
                            Log.v("FYI", "Error: " + error.toString());
                        }
                    }
            );
        }

        return rootView;
    }

    private void initCard(){

        ArrayList<Card> cards = new ArrayList<Card>();

        try {
            Dao<Panel, String> panelDao = getHelper().getPanelDao();
            panelList = panelDao.queryForAll();
        }
        catch(SQLException se){

        }

        for(int i = 0; i < panelList.size();i++){
            CardBody card = new CardBody(getActivity(),panelList.get(i));
            cards.add(card);
        }

        if(getActivity() != null) {
            CardArrayAdapter adapter = new CardArrayAdapter(getActivity(), cards);
            CardListView cardList = (CardListView) getActivity().findViewById(R.id.panel_list);
            cardList.setAdapter(adapter);
        }
    }

    public DatabaseHelper getHelper(){
        if(dbHelper == null){
            dbHelper = OpenHelperManager.getHelper(getActivity(),DatabaseHelper.class);
        }
        return dbHelper;
    }

    class CardBody extends Card{
        ImageView m_Rectangle;
        TextView m_PanelName;
        TextView m_PanelDescription;
        TextView m_PanelTime;
        TextView m_PanelLocation;
        CheckBox m_Favorited;

        Panel panel;
        GregorianCalendar date;

        public CardBody(Context context, Panel panel){
            super(context,R.layout.card_panel_inner);
            this.panel = panel;
        }

        @Override
        public void setupInnerViewElements(ViewGroup vg, View v){
            m_Rectangle = (ImageView) v.findViewById(R.id.colorBorder);
            m_PanelName = (TextView) v.findViewById(R.id.card_panel_name);
            m_PanelDescription = (TextView) v.findViewById(R.id.card_main_inner_simple_title);
            m_PanelTime = (TextView) v.findViewById(R.id.card_time);
            m_PanelLocation = (TextView) v.findViewById(R.id.card_panel_location);
            m_Favorited = (CheckBox) v.findViewById(R.id.favorited);

            m_Rectangle.setBackgroundColor(Color.rgb(255,0,0));

            m_PanelName.setText(panel.getM_sPanelName());
            m_PanelDescription.setText(panel.getM_sPanelDescription());
            date = new GregorianCalendar();
            date.setTimeInMillis(panel.m_PanelTime);
            m_PanelTime.setText(date.getTime().toString());
            m_PanelLocation.setText(panel.getM_sPanelRoom());

        }
    }
}
