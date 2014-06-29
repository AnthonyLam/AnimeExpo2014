package com.lamapress.animeexpo2014.axapp.ui;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.lamapress.animeexpo2014.axapp.R;
import com.lamapress.animeexpo2014.axapp.core.Panel;
import com.lamapress.animeexpo2014.axapp.sqlite_helper.DatabaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.Response;

/**
 * Created by Anthony on 6/28/2014.
 */
public class ScheduleFragment extends Fragment {

    public static final String ARG_SECTION = "section_number";
    DatabaseHelper dbHelper =null;
    List<Panel> panelList = new ArrayList<Panel>();

    public ScheduleFragment(){

    }

    public static ScheduleFragment newInstance(int sectionNumber){
        ScheduleFragment fragment = new ScheduleFragment();

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
        }
        return rootView;
    }

    private void initCard(){

        ArrayList<Card> cards = new ArrayList<Card>();

        try {
            Dao<Panel, String> panelDao = getHelper().getPanelDao();
            QueryBuilder<Panel,String> qb = panelDao.queryBuilder();
            qb.where().eq("favorited",true);
        }
        catch(SQLException se){
        }

        for(int i = 0; i < panelList.size(); i++){
            CardBody card = new CardBody(getActivity(),panelList.get(i));
            cards.add(card);
        }

        if(getActivity() != null){
            CardArrayAdapter adapter = new CardArrayAdapter(getActivity(),cards);
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
}
